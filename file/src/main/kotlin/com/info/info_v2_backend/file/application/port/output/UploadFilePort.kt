package com.info.info_v2_backend.file.application.port.output

import com.info.info_v2_backend.common.file.FileDto
import org.springframework.web.multipart.MultipartFile

interface UploadFilePort {

    fun upload(file: MultipartFile, rootPathName: String, middlePathName: String): FileDto
}