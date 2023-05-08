package com.info.info_v2_backend.file.application.service

import com.info.info_v2_backend.common.file.dto.FileDto
import com.info.info_v2_backend.common.file.dto.request.GenerateFileRequest
import com.info.info_v2_backend.file.application.port.input.UploadFileUsecase
import com.info.info_v2_backend.file.application.port.output.UploadFilePort
import org.springframework.stereotype.Service
import java.util.*

@Service
class UploadFile(
    private val uploadFilePort: UploadFilePort
): UploadFileUsecase {
    override fun upload(request: GenerateFileRequest, rootPathName: String, middlePathName: String): FileDto {
        val fileId = UUID.randomUUID().toString()
        val dto = uploadFilePort.getPresignedUrl(request.fileName, request.contentType, rootPathName, "${middlePathName}", fileId)
        dto.resetAuthenticatedUri(dto.fileUrl)
        dto.removeParameter()
        return dto
    }

}
