package com.info.info_v2_backend.file.application.port.input

import com.info.info_v2_backend.common.file.dto.FileDto
import com.info.info_v2_backend.common.file.dto.request.GenerateFileRequest

interface UploadFileUsecase {

    fun upload(request: GenerateFileRequest, rootPathName: String, middlePathName: String): FileDto
}
