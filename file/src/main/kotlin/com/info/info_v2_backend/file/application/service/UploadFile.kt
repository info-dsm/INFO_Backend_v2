package com.info.info_v2_backend.file.application.service

import com.info.info_v2_backend.common.file.CompanyFileClassificationType
import com.info.info_v2_backend.common.file.RegisterCompanyFileDto
import com.info.info_v2_backend.file.application.port.input.UploadCompanyFileUsecase
import com.info.info_v2_backend.file.application.port.output.RegisterCompanyFilePort
import com.info.info_v2_backend.file.application.port.output.SaveCompanyFilePort
import com.info.info_v2_backend.file.application.port.output.UploadFilePort
import com.info.info_v2_backend.file.domain.company.CompanyFile
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class UploadFile(
    private val uploadFilePort: UploadFilePort,
    private val saveFilePort: SaveCompanyFilePort,
    private val registerCompanyFilePort: RegisterCompanyFilePort
): UploadCompanyFileUsecase {

    override fun uploadCompanyFile(file: MultipartFile, classification: CompanyFileClassificationType, companyId: String) {
        val dto = uploadFilePort.upload(file, "COMPANY/${companyId}", classification.name)
        val companyFile = CompanyFile(
            dto,
            classification,
            companyId
        )

        saveFilePort.save(
            companyFile
        )

        registerCompanyFilePort.registerCompanyFile(
            RegisterCompanyFileDto(
                companyFile.id,
                companyFile.companyId,
                companyFile.companyFileClassification
            )
        )
    }
}