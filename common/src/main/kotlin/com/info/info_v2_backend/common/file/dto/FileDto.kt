package com.info.info_v2_backend.common.file.dto

import com.info.info_v2_backend.common.file.FileConvert.getUrlWithoutParameters
import com.info.info_v2_backend.common.file.dto.type.FileType

class FileDto(
    fileId: String,
    fileUrl: String,
    fileType: FileType,
    extension: String,
    fileName: String
) {
    val fileId: String = fileId
    var fileUrl: String = fileUrl
    val fileType: FileType = fileType
    val extension: String = extension
    val fileName: String = fileName
    var authenticatedUri: String? = null
        protected set

    fun resetAuthenticatedUri(uri: String) {
        this.authenticatedUri = uri
    }

    fun removeParameter(){
        this.fileUrl = getUrlWithoutParameters(this.fileUrl)
    }

}
