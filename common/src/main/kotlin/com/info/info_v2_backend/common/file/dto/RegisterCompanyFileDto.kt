package com.info.info_v2_backend.common.file.dto

import com.info.info_v2_backend.common.file.dto.CompanyFileClassificationType

data class RegisterCompanyFileDto(
    val fileId: String,
    val companyId: String,
    val companyFileClassificationType: CompanyFileClassificationType
)