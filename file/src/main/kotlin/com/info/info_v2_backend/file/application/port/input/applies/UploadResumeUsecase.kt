package com.info.info_v2_backend.file.application.port.input.applies

import com.info.info_v2_backend.common.file.dto.request.GenerateFileRequest
import com.info.info_v2_backend.common.file.dto.response.PresignedUrlResponse

interface UploadResumeUsecase {

    fun uploadResume(request: GenerateFileRequest, noticeId: String, studentEmail: String): PresignedUrlResponse
}