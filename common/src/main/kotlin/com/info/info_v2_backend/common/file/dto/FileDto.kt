package com.info.info_v2_backend.common.file.dto

import com.info.info_v2_backend.common.file.dto.type.FileType

class FileDto(
    fileUrl: String,
    fileType: FileType,
    extension: String,
    fileName: String

) {
    val fileUrl: String = fileUrl
    val fileType: FileType = fileType
    val extension: String = extension
    val fileName: String = fileName

}