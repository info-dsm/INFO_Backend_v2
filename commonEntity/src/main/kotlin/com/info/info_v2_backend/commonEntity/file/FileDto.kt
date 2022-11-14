package com.info.info_v2_backend.commonEntity.file

data class FileDto(
    val fileUrl: String,
    val fileType: FileType,
    val extension: String,
    val fileName: String
) {

    override fun toString(): String {
        return "url: $fileUrl, type: $fileType, extension: $extension"
    }
}
