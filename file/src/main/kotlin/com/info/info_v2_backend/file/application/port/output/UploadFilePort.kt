package com.info.info_v2_backend.file.application.port.output

import com.info.info_v2_backend.common.file.dto.FileDto
import org.springframework.web.multipart.MultipartFile

interface UploadFilePort {

    fun getPresignedUrl(originalFileName: String, contentType: String, rootPathName: String, middlePathName: String): FileDto
}