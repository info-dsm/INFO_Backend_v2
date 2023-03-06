package com.info.info_v2_backend.company.application.service

import com.info.info_v2_backend.common.auth.Auth
import com.info.info_v2_backend.common.auth.AuthenticationCodeDto
import com.info.info_v2_backend.common.auth.AuthenticationCodeType
import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.common.exception.ErrorCode
import com.info.info_v2_backend.common.file.dto.CompanyFileClassificationType
import com.info.info_v2_backend.common.file.dto.request.GenerateFileRequest
import com.info.info_v2_backend.common.file.dto.response.PresignedUrlListResponse
import com.info.info_v2_backend.common.file.dto.response.PresignedUrlResponse
import com.info.info_v2_backend.company.adapter.input.web.rest.dto.request.register.RegisterCompanyRequest
import com.info.info_v2_backend.company.application.port.input.RegisterCompanyUsecase
import com.info.info_v2_backend.company.application.port.output.*
import com.info.info_v2_backend.company.application.port.output.businessArea.LoadBusinessAreaPort
import com.info.info_v2_backend.company.application.port.output.businessArea.SaveBusinessAreaTaggedPort
import com.info.info_v2_backend.company.application.port.output.company.LoadCompanyPort
import com.info.info_v2_backend.company.application.port.output.company.SaveCompanyPort
import com.info.info_v2_backend.company.application.port.output.company.SaveContactorPort
import com.info.info_v2_backend.company.application.port.output.document.SaveCompanyDocumentPort
import com.info.info_v2_backend.company.application.port.output.file.CompanyFilePort
import com.info.info_v2_backend.company.domain.Company
import com.info.info_v2_backend.company.domain.businessArea.BusinessAreaTagged
import com.info.info_v2_backend.company.domain.document.CompanyDocument
import com.info.info_v2_backend.company.domain.introduction.CompanyIntroduction
import com.info.info_v2_backend.user.adapter.input.web.rest.dto.request.SaveContactorDto
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class RegisterCompany(
    private val companyFilePort: CompanyFilePort,
    private val saveCompanyPort: SaveCompanyPort,
    private val saveContactorPort: SaveContactorPort,
    private val checkEmailCodePort: CheckEmailCodePort,
    private val saveBusinessAreaTaggedPort: SaveBusinessAreaTaggedPort,
    private val loadBusinessAreaPort: LoadBusinessAreaPort,
    private val loadCompanyPort: LoadCompanyPort,
    private val saveCompanyDocumentPort: SaveCompanyDocumentPort
): RegisterCompanyUsecase {

    private val log = LoggerFactory.getLogger(this.javaClass)

    override fun register(
        emailCheckCode: String,
        request: RegisterCompanyRequest
    ): PresignedUrlListResponse {
        if (checkEmailCodePort.check(
                AuthenticationCodeDto(
                    request.companyContact.email,
                    emailCheckCode,
                    AuthenticationCodeType.SIGNUP_EMAIL
                )
            ) || Auth.checkIsTeacher()) {

            loadCompanyPort.loadCompany(request.companyNumber)?.let {
                throw BusinessException("이미 존재하는 사업자등록번호입니다.", ErrorCode.ALREADY_EXISTS_ERROR)
            }

            saveContactorPort.save(
                SaveContactorDto(
                    request.companyContact.contactorName?:"",
                    request.companyContact.email,
                    request.companyContact.password,
                    request.companyContact.contactorRank,
                    request.companyContact.contactorPhone,
                    request.companyContact.passwordHint,
                    request.companyNumber
                )
            )

            val list: MutableList<PresignedUrlResponse> = ArrayList()
            val businessCertificateFile = request.businessRegisteredCertificate.let {
                uploadFile(
                    CompanyFileClassificationType.BUSINESS_CERTIFICATE,
                    request.companyNumber,
                    it
                )
            }

            val logoFile = request.companyLogo.let {
                uploadFile(
                    CompanyFileClassificationType.COMPANY_LOGO,
                    request.companyNumber,
                    it
                )
            }

            val companyIntroductionFileList = request.companyIntroductionFile.request.map {
                uploadFile(
                    CompanyFileClassificationType.COMPANY_INTRODUCTION,
                    request.companyNumber,
                    it
                )
            }

            val companyPhotoFileList = request.companyPhotoList.request.map {
                uploadFile(
                    CompanyFileClassificationType.COMPANY_PHOTO,
                    request.companyNumber,
                    it
                )
            }

            val company = Company(
                request.companyNumber,
                request.companyNameRequest.toCompanyName(),
                request.companyInformation.toCompanyInformation(),
                request.companyContact.toContactorId(),
                CompanyIntroduction(
                    request.introduction
                )
            )
            saveCompanyPort.save(company)

            request.businessAreaList.map {
                saveBusinessAreaTaggedPort.saveBusinessAreaTagged(
                    BusinessAreaTagged(
                        loadBusinessAreaPort.loadBusinessArea(it)
                            ?: throw BusinessException(
                                "존재하는 사업분야가 아닙니다. ->$it",
                                ErrorCode.PERSISTENCE_DATA_NOT_FOUND_ERROR
                            )
                        ,
                        company
                    )
                )
            }

            saveCompanyPort.save(company)
            saveCompanyDocumentPort.save(
                CompanyDocument(
                    company.companyName.companyName,
                    company.companyNumber
                )
            )
            list.add(
                businessCertificateFile
            )
            list.add(
                logoFile
            )
            list.addAll(
                companyIntroductionFileList
            )
            list.addAll(
                companyPhotoFileList
            )
            return PresignedUrlListResponse(
                list
            )
        } else throw BusinessException("인증번호가 올바르지 않습니다. -> ${emailCheckCode}", ErrorCode.INVALID_INPUT_DATA_ERROR)
    }

    private fun uploadFile(
        classificationType: CompanyFileClassificationType,
        companyNumber: String,
        geneFileRequest: GenerateFileRequest
    ): PresignedUrlResponse {
        val presignedUrlResponse = companyFilePort.upload(
            companyNumber,
            classificationType,
            geneFileRequest
        )
        log.info("companyNumber: $companyNumber, fileName: ${presignedUrlResponse.fileName}, url: ${presignedUrlResponse.url}")
        return presignedUrlResponse
    }
}