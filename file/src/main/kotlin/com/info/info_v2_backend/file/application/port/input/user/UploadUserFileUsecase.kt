package com.info.info_v2_backend.file.application.port.input.user

import com.info.info_v2_backend.common.file.dto.request.GenerateFileRequest
import com.info.info_v2_backend.common.file.dto.response.PresignedUrlResponse

interface UploadUserFileUsecase {

    fun uploadUserProfilePhoto(request: GenerateFileRequest, userEmail: String): PresignedUrlResponse
}
