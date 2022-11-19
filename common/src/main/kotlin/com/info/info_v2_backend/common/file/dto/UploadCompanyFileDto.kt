package com.info.info_v2_backend.common.file.dto

import com.info.info_v2_backend.common.file.dto.CompanyFileClassificationType

data class UploadCompanyFileDto(
    val classification: CompanyFileClassificationType,
    val companyId: String
)