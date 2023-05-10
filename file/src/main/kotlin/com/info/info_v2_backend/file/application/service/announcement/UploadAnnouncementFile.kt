package com.info.info_v2_backend.file.application.service.announcement

import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.common.exception.ErrorCode
import com.info.info_v2_backend.common.file.dto.request.GenerateFileRequest
import com.info.info_v2_backend.common.file.dto.response.PresignedUrlResponse
import com.info.info_v2_backend.file.application.port.input.UploadFileUsecase
import com.info.info_v2_backend.file.application.port.input.announce.UploadAnnouncementFileUsecase
import com.info.info_v2_backend.file.application.port.output.announce.SaveAnnouncementFilePort
import com.info.info_v2_backend.file.domain.announcement.AnnouncementFile
import org.springframework.stereotype.Service

@Service
class UploadAnnouncementFile(
    private val saveAnnouncementFilePort: SaveAnnouncementFilePort,
    private val uploadFileUsecase: UploadFileUsecase
): UploadAnnouncementFileUsecase {
    override fun upload(announcementId: Long, request: GenerateFileRequest): PresignedUrlResponse {
        val dto = uploadFileUsecase.upload(request, "ANNOUNCEMENT/${announcementId}", "ANNOUNCEMENT")
        val announcementFile = AnnouncementFile(
            dto,
            announcementId
        )
        saveAnnouncementFilePort.save(
            announcementFile
        )
        return PresignedUrlResponse(
            dto.authenticatedUri?: throw BusinessException(errorCode = ErrorCode.UNDEFINED_ERROR),
            dto.fileName
        )
    }

}
