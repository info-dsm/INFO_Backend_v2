package com.info.info_v2_backend.file.application.service

import com.info.info_v2_backend.common.file.CompanyFileClassificationType
import com.info.info_v2_backend.common.file.RegisterCompanyFileDto
import com.info.info_v2_backend.file.application.port.input.UploadCompanyFileUsecase
import com.info.info_v2_backend.file.application.port.output.SaveCompanyFilePort
import com.info.info_v2_backend.file.application.port.output.UploadFilePort
import com.info.info_v2_backend.file.domain.company.CompanyFile
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.util.*

@Service
class UploadFile(
    private val uploadFilePort: UploadFilePort,
    private val saveFilePort: SaveCompanyFilePort,
): UploadCompanyFileUsecase {

    override fun uploadCompanyFile(file: MultipartFile, classification: CompanyFileClassificationType, companyId: String): String {
        val fileId = UUID.randomUUID().toString()
        val dto = uploadFilePort.upload(file, "COMPANY/${companyId}", "${classification.name}/${fileId}")
        val companyFile = CompanyFile(
            fileId,
            dto,
            classification,
            companyId
        )

        saveFilePort.save(
            companyFile
        )

        return fileId
    }
}