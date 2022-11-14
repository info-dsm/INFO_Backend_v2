package com.info.info_v2_backend.common.file

import com.info.info_v2_backend.common.file.type.FileType

data class RegisterCompanyFileDto(
    val fileId: String,
    val companyId: String,
    val companyFileClassificationType: CompanyFileClassificationType
)