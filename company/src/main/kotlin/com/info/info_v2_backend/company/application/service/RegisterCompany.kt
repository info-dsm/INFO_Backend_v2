package com.info.info_v2_backend.company.application.service

import com.info.info_v2_backend.common.auth.AuthenticationCodeDto
import com.info.info_v2_backend.common.auth.AuthenticationCodeType
import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.common.exception.ErrorCode
import com.info.info_v2_backend.common.file.CompanyFileClassificationType
import com.info.info_v2_backend.company.adapter.input.web.rest.dto.RegisterCompanyRequest
import com.info.info_v2_backend.company.application.port.input.RegisterCompanyUsecase
import com.info.info_v2_backend.company.application.port.output.*
import com.info.info_v2_backend.company.application.port.output.businessArea.LoadBusinessAreaPort
import com.info.info_v2_backend.company.application.port.output.businessArea.SaveBusinessAreaPort
import com.info.info_v2_backend.company.application.port.output.businessArea.SaveBusinessAreaTaggedPort
import com.info.info_v2_backend.company.application.port.output.company.SaveCompanyPort
import com.info.info_v2_backend.company.application.port.output.company.SaveContactorPort
import com.info.info_v2_backend.company.application.port.output.file.FilePort
import com.info.info_v2_backend.company.domain.Company
import com.info.info_v2_backend.company.domain.businessArea.BusinessArea
import com.info.info_v2_backend.company.domain.businessArea.BusinessAreaTagged
import com.info.info_v2_backend.company.domain.introduction.CompanyIntroduction
import com.info.info_v2_backend.user.adapter.input.event.dto.ContactorDto
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class RegisterCompany(
    private val uploadFilePort: FilePort,
    private val saveCompanyPort: SaveCompanyPort,
    private val saveContactorPort: SaveContactorPort,
    private val checkEmailCodePort: CheckEmailCodePort,
    private val saveBusinessAreaPort: SaveBusinessAreaPort,
    private val loadBusinessAreaPort: LoadBusinessAreaPort,
    private val saveBusinessAreaTaggedPort: SaveBusinessAreaTaggedPort
): RegisterCompanyUsecase {

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
            )) {

            saveContactorPort.save(
                ContactorDto(
                    request.companyContact.contactorName,
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
                ),
                request.isLeading
            )

            request.businessAreaList.map {
                saveBusinessAreaTaggedPort.saveBusinessAreaTagged(
                    BusinessAreaTagged(
                        loadBusinessAreaPort.loadBusinessArea(it)
                            ?: saveBusinessAreaPort.saveBusinessArea(BusinessArea(it))
                       ,
                        company
                    )
                )
            }

            saveCompanyPort.save(company)


            company.companyIntroduction.changeCompanyIntroductionFile(
                uploadFile(businessRegisteredCertificate, CompanyFileClassificationType.BUSINESS_CERTIFICATE, request.companyNumber),
                CompanyFileClassificationType.BUSINESS_CERTIFICATE
            )

            company.companyIntroduction.changeCompanyIntroductionFile(
                uploadFile(companyLogo, CompanyFileClassificationType.COMPANY_LOGO, request.companyNumber),
                CompanyFileClassificationType.COMPANY_LOGO
            )
            saveCompanyPort.save(company)

            companyPhotoList.map {
                uploadFile(it, CompanyFileClassificationType.COMPANY_PHOTO, request.companyNumber)
            }
            companyIntroductionFile.map {
                uploadFile(it, CompanyFileClassificationType.COMPANY_INTRODUCTION, request.companyNumber)
            }


        } else throw BusinessException("인증번호가 올바르지 않습니다. -> ${emailCheckCode}", ErrorCode.INVALID_INPUT_DATA_ERROR)
    }

    private fun uploadFile(file: MultipartFile, classificationType: CompanyFileClassificationType, companyId: String): String {
        return uploadFilePort.upload(
            companyId,
            classificationType,
            file
        )
    }
}