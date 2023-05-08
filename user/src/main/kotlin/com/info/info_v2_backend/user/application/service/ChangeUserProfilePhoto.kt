package com.info.info_v2_backend.user.application.service

import com.info.info_v2_backend.common.file.dto.request.GenerateFileRequest
import com.info.info_v2_backend.common.file.dto.response.PresignedUrlResponse
import com.info.info_v2_backend.user.application.port.input.ChangeUserProfilePhotoUsecase
import com.info.info_v2_backend.user.application.port.output.UserFilePort
import org.springframework.stereotype.Service

@Service
class ChangeUserProfilePhoto(
    private val userFilePort: UserFilePort
): ChangeUserProfilePhotoUsecase {

    override fun upload(request: GenerateFileRequest, userEmail: String): PresignedUrlResponse {
        return userFilePort.upload(request, userEmail)
    }
}
