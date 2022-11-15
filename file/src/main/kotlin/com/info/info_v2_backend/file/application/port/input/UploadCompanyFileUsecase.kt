package com.info.info_v2_backend.file.application.port.input

import com.info.info_v2_backend.common.file.CompanyFileClassificationType
import org.springframework.web.multipart.MultipartFile

interface UploadCompanyFileUsecase {

    fun uploadCompanyFile(file: MultipartFile, classification: CompanyFileClassificationType, companyId: String): String
}