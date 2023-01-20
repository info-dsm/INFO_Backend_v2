package com.info.info_v2_backend.common.file.dto.request

data class GenerateFileRequest (
    val fileName: String,
    val contentType: String
)