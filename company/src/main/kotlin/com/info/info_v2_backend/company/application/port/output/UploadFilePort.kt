package com.info.info_v2_backend.company.application.port.output

import com.info.info_v2_backend.commonEntity.file.File
import org.springframework.web.multipart.MultipartFile

interface UploadFilePort {

    fun upload(file: MultipartFile): String
}