package com.info.info_v2_backend.common.file

import com.info.info_v2_backend.common.file.type.FileType

class FileDto(
    val fileUrl: String,
    val fileType: FileType,
    val extension: String,
    val fileName: String
) {

    override fun toString(): String {
        return "url: $fileUrl, type: $fileType, extension: $extension"
    }
}
