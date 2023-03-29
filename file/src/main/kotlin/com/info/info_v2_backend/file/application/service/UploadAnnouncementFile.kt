package com.info.info_v2_backend.file.application.service

import com.info.info_v2_backend.common.file.dto.request.GenerateFileListRequest
import com.info.info_v2_backend.common.file.dto.response.PresignedUrlListResponse
import com.info.info_v2_backend.file.application.port.input.announce.UploadAnnouncementFileUsecase
import org.springframework.stereotype.Service

@Service
class UploadAnnouncementFile: UploadAnnouncementFileUsecase {
    override fun upload(announcementId: Long, request: GenerateFileListRequest): PresignedUrlListResponse {
        TODO("Not yet implemented")
    }


}