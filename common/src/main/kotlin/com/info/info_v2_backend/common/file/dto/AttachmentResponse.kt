package com.info.info_v2_backend.common.file.dto

import com.info.info_v2_backend.common.file.dto.type.FileType

data class AttachmentResponse(
    val fileId: String,
    val fileUrl: String,
    val fileType: FileType,
    val extension: String,
    val fileName: String,
    val noticeId: String
)
