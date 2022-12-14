package com.info.info_v2_backend.company.application.service

import com.info.info_v2_backend.common.auth.Auth
import com.info.info_v2_backend.common.auth.AuthenticationCodeDto
import com.info.info_v2_backend.common.auth.AuthenticationCodeType
import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.common.exception.ErrorCode
import com.info.info_v2_backend.common.file.FileConvert
import com.info.info_v2_backend.common.file.dto.CompanyFileClassificationType
import com.info.info_v2_backend.company.adapter.input.web.rest.dto.request.register.RegisterCompanyRequest
import com.info.info_v2_backend.company.application.port.input.RegisterCompanyUsecase
import com.info.info_v2_backend.company.application.port.output.*
import com.info.info_v2_backend.company.application.port.output.businessArea.LoadBusinessAreaPort
import com.info.info_v2_backend.company.application.port.output.businessArea.SaveBusinessAreaPort
import com.info.info_v2_backend.company.application.port.output.businessArea.SaveBusinessAreaTaggedPort
import com.info.info_v2_backend.company.application.port.output.company.LoadCompanyPort
import com.info.info_v2_backend.company.application.port.output.company.SaveCompanyPort
import com.info.info_v2_backend.company.application.port.output.company.SaveContactorPort
import com.info.info_v2_backend.company.application.port.output.document.SaveCompanyDocumentPort
import com.info.info_v2_backend.company.application.port.output.file.CompanyFilePort
import com.info.info_v2_backend.company.domain.Company
import com.info.info_v2_backend.company.domain.businessArea.BusinessArea
import com.info.info_v2_backend.company.domain.businessArea.BusinessAreaTagged
import com.info.info_v2_backend.company.domain.document.CompanyDocument
import com.info.info_v2_backend.company.domain.introduction.CompanyIntroduction
import com.info.info_v2_backend.user.adapter.input.web.rest.dto.request.SaveContactorDto
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile

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

    companion object {
        val COMPANY_FILE_PATH = "/tmp/spring/company"
    }

    override fun register(
        emailCheckCode: String,
        request: RegisterCompanyRequest,
        businessRegisteredCertificate: MultipartFile,
        companyIntroductionFile: List<MultipartFile>,
        companyLogo: MultipartFile,
        companyPhotoList: List<MultipartFile>
    ) {
        if (checkEmailCodePort.check(
                AuthenticationCodeDto(
                    request.companyContact.email,
                    emailCheckCode,
                    AuthenticationCodeType.SIGNUP_EMAIL
                )
            ) || Auth.checkIsTeacher()) {

            loadCompanyPort.loadCompany(request.companyNumber)?.let {
                throw BusinessException("?????? ???????????? ??????????????????????????????.", ErrorCode.ALREADY_EXISTS_ERROR)
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
                                "???????????? ??????????????? ????????????. ->$it",
                                ErrorCode.PERSISTENCE_DATA_NOT_FOUND_ERROR
                            )
                        ,
                        company
                    )
                )
            }

            saveCompanyPort.save(company)

            try {
                uploadFile(
                    FileConvert.fileToMultipartFileConvert(
                        FileConvert.multipartFileToFileConvert(businessRegisteredCertificate, "$COMPANY_FILE_PATH/")
                    ), CompanyFileClassificationType.BUSINESS_CERTIFICATE, request.companyNumber
                )
                uploadFile(
                    FileConvert.fileToMultipartFileConvert(
                        FileConvert.multipartFileToFileConvert(companyLogo, "$COMPANY_FILE_PATH/")
                    ), CompanyFileClassificationType.COMPANY_LOGO, request.companyNumber
                )
                FileConvert.removeLocalFile("$COMPANY_FILE_PATH/${businessRegisteredCertificate.originalFilename}")
                FileConvert.removeLocalFile("$COMPANY_FILE_PATH/${companyLogo.originalFilename}")

                companyPhotoList.map {
                    uploadFile(
                        FileConvert.fileToMultipartFileConvert(
                            FileConvert.multipartFileToFileConvert(it, "$COMPANY_FILE_PATH/")
                        ), CompanyFileClassificationType.COMPANY_PHOTO, request.companyNumber
                    )
                    FileConvert.removeLocalFile("$COMPANY_FILE_PATH/${it.originalFilename}")
                }
                companyIntroductionFile.map {
                    uploadFile(
                        FileConvert.fileToMultipartFileConvert(
                            FileConvert.multipartFileToFileConvert(it, "$COMPANY_FILE_PATH/")
                        ), CompanyFileClassificationType.COMPANY_INTRODUCTION, request.companyNumber
                    )
                    FileConvert.removeLocalFile("$COMPANY_FILE_PATH/${it.originalFilename}")
                }
            } catch (e: java.lang.RuntimeException) {
                company.makeFailed()
                saveCompanyPort.save(company)
                throw BusinessException("????????? ???????????? ??? ????????? ??????????????????.", ErrorCode.BAD_GATEWAY_ERROR)
            }

            saveCompanyDocumentPort.save(
                CompanyDocument(
                    company.companyName.companyName,
                    company.companyNumber
                )
            )
        } else throw BusinessException("??????????????? ???????????? ????????????. -> ${emailCheckCode}", ErrorCode.INVALID_INPUT_DATA_ERROR)
    }

    private fun uploadFile(file: MultipartFile, classificationType: CompanyFileClassificationType, companyId: String) {
        return companyFilePort.upload(
            companyId,
            classificationType,
            file
        )
    }
}