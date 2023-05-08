package com.info.info_v2_backend.user.application.port.output

import com.info.info_v2_backend.common.file.dto.request.GenerateFileRequest
import com.info.info_v2_backend.common.file.dto.response.PresignedUrlResponse
import com.info.info_v2_backend.common.file.dto.response.UserFileResponse

interface UserFilePort {

    fun loadProfilePhoto(email: String): UserFileResponse?
    fun upload(request: GenerateFileRequest, userEmail: String): PresignedUrlResponse
}
