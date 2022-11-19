package com.info.info_v2_backend.common.file.dto.response

import com.info.info_v2_backend.common.file.dto.CompanyFileClassificationType
import com.info.info_v2_backend.common.file.dto.type.FileType

data class CompanyFileResponse(
    val fileId: String,
    val fileUrl: String,
    val fileType: FileType,
    val extension: String,
    val fileName: String,
    val companyNumber: String,
    val companyFileClassificationType: CompanyFileClassificationType
)
