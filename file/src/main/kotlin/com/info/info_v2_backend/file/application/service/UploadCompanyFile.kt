package com.info.info_v2_backend.file.application.service

import com.info.info_v2_backend.common.file.dto.CompanyFileClassificationType
import com.info.info_v2_backend.common.file.dto.RegisterCompanyFileDto
import com.info.info_v2_backend.file.application.port.input.company.UploadCompanyFileUsecase
import com.info.info_v2_backend.file.application.port.output.company.RegisterCompanyFilePort
import com.info.info_v2_backend.file.application.port.output.UploadFilePort
import com.info.info_v2_backend.file.application.port.output.company.RemoveCompanyFilePort
import com.info.info_v2_backend.file.application.port.output.company.SaveCompanyFilePort
import com.info.info_v2_backend.file.domain.company.CompanyFile
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.util.*


@Service
class UploadCompanyFile(
    private val uploadFilePort: UploadFilePort,
    private val saveCompanyFilePort: SaveCompanyFilePort,
    private val removeCompanyFilePort: RemoveCompanyFilePort,
    private val registerCompanyFilePort: RegisterCompanyFilePort,
): UploadCompanyFileUsecase {

    @Async
    override fun uploadCompanyFile(file: MultipartFile, classification: CompanyFileClassificationType, companyNumber: String) {
        val fileId = UUID.randomUUID().toString()
        val dto = uploadFilePort.upload(file, "COMPANY/${companyNumber}", "${classification.name}/${fileId}")
        val companyFile = CompanyFile(
            fileId,
            dto,
            classification,
            companyNumber
        )
        if (classification == CompanyFileClassificationType.COMPANY_LOGO
            || classification == CompanyFileClassificationType.BUSINESS_CERTIFICATE
        ) {
            registerCompanyFilePort.register(
                RegisterCompanyFileDto(
                    fileId,
                    companyNumber,
                    classification
                )
            )
        }

        removeCompanyFilePort.remove(classification, companyNumber)
        saveCompanyFilePort.save(
            companyFile
        )
    }


}