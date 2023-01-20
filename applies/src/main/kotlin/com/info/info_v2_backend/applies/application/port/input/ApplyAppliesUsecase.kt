package com.info.info_v2_backend.applies.application.port.input

import com.info.info_v2_backend.common.file.dto.request.GenerateFileRequest
import com.info.info_v2_backend.common.file.dto.response.PresignedUrlResponse

interface ApplyAppliesUsecase {

    fun apply(noticeId: String, request: GenerateFileRequest, studentEmail: String): PresignedUrlResponse
}