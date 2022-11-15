package com.info.info_v2_backend.common.file

import org.springframework.web.multipart.MultipartFile

data class UploadCompanyFileDto(
    val classification: CompanyFileClassificationType,
    val companyId: String
)