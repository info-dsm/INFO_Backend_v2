package com.info.info_v2_backend.company.application.service

import com.info.info_v2_backend.common.auth.AuthenticationCodeDto
import com.info.info_v2_backend.common.auth.AuthenticationCodeType
import com.info.info_v2_backend.company.adapter.input.web.rest.dto.RegisterCompanyRequest
import com.info.info_v2_backend.company.application.port.input.RegisterCompanyUsecase
import com.info.info_v2_backend.company.application.port.output.CheckEmailCodePort
import com.info.info_v2_backend.company.application.port.output.SaveCompanyPort
import com.info.info_v2_backend.company.application.port.output.SaveContactorPort
import com.info.info_v2_backend.company.application.port.output.UploadFilePort
import com.info.info_v2_backend.company.domain.Company
import com.info.info_v2_backend.company.domain.introduction.CompanyIntroduction
import com.info.info_v2_backend.user.adapter.input.event.dto.ContactorDto
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class RegisterCompany(
    private val uploadFilePort: UploadFilePort,
    private val saveCompanyPort: SaveCompanyPort,
    private val saveContactorPort: SaveContactorPort,
    private val checkEmailCodePort: CheckEmailCodePort,

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
                    request.companyContact.contactorPhone
                )
            )
            saveCompanyPort.save(
                Company(
                    request.companyId,
                    request.companyNameRequest.toCompanyName(),
                    request.companyInformation.toCompanyInformation(),
                    request.companyContact.toContactorId(),
                    CompanyIntroduction(
                        request.introduction
                    ),
                    request.isLeading
                )
            )

        }
    }
}