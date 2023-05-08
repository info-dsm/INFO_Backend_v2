package com.info.info_v2_backend.user.application.port.input

import com.info.info_v2_backend.common.file.dto.request.GenerateFileListRequest
import com.info.info_v2_backend.common.file.dto.request.GenerateFileRequest
import com.info.info_v2_backend.common.file.dto.response.PresignedUrlResponse

interface ChangeUserProfilePhotoUsecase {

    fun upload(request: GenerateFileRequest, userEmail: String): PresignedUrlResponse
}
