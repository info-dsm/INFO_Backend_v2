package com.info.info_v2_backend.file.application.service

import com.info.info_v2_backend.common.file.dto.request.GenerateFileRequest
import com.info.info_v2_backend.common.file.dto.response.PresignedUrlResponse
import com.info.info_v2_backend.file.application.port.input.announce.UploadAnnouncementFileUsecase
import com.info.info_v2_backend.file.application.port.output.UploadFilePort
import com.info.info_v2_backend.file.application.port.output.announce.UploadAnnouncementFilePort
import com.info.info_v2_backend.file.domain.announcement.AnnouncementFile
import org.springframework.stereotype.Service
import java.util.*

@Service
class UploadAnnouncementFile(
    private val uploadAnnouncementFilePort: UploadAnnouncementFilePort,
    private val uploadFilePort:UploadFilePort
): UploadAnnouncementFileUsecase {
    override fun upload(announcementId: Long, request: GenerateFileRequest): PresignedUrlResponse {
        val fileId = UUID.randomUUID().toString()
        val dto = uploadFilePort.getPresignedUrl(request.fileName, request.contentType, "ANNOUNCEMENT/${announcementId}", "ANNOUNCEMENT/${fileId}")
        val authUrl = dto.fileUrl
        dto.removeParameter()
        val announcementFile = AnnouncementFile(
            fileId,
            dto,
            announcementId
        )
        uploadAnnouncementFilePort.upload(
            announcementFile
        )
        return PresignedUrlResponse(
            authUrl,
            dto.fileName
        )
    }

}