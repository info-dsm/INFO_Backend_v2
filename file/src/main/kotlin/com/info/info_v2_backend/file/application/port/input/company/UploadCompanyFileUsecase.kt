package com.info.info_v2_backend.file.application.port.input.company

import com.info.info_v2_backend.common.file.dto.CompanyFileClassificationType
import org.springframework.web.multipart.MultipartFile

interface UploadCompanyFileUsecase {

    fun uploadCompanyFile(file: MultipartFile, classification: CompanyFileClassificationType, companyNumber: String)
}