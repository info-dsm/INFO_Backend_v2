package com.info.info_v2_backend.file.application.port.input.announce

import com.info.info_v2_backend.common.file.dto.request.GenerateFileListRequest
import com.info.info_v2_backend.common.file.dto.request.GenerateFileRequest
import com.info.info_v2_backend.common.file.dto.response.PresignedUrlListResponse
import com.info.info_v2_backend.common.file.dto.response.PresignedUrlResponse

interface UploadAnnouncementFileUsecase {

    fun upload(announcementId: Long, request: GenerateFileRequest): PresignedUrlResponse
}