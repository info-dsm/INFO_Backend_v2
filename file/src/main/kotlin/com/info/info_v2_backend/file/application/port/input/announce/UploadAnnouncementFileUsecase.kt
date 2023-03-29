package com.info.info_v2_backend.file.application.port.input.announce

import com.info.info_v2_backend.common.file.dto.request.GenerateFileListRequest
import com.info.info_v2_backend.common.file.dto.response.PresignedUrlListResponse

interface UploadAnnouncementFileUsecase {

    fun upload(announcementId: Long, request: GenerateFileListRequest): PresignedUrlListResponse
}