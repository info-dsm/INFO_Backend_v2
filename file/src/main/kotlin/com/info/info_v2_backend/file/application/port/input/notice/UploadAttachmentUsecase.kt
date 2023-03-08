package com.info.info_v2_backend.file.application.port.input.notice

import com.info.info_v2_backend.common.file.dto.request.GenerateFileRequest
import com.info.info_v2_backend.common.file.dto.response.PresignedUrlResponse

interface UploadAttachmentUsecase {

    fun uploadAttachment(request: GenerateFileRequest, noticeId: String): PresignedUrlResponse
}