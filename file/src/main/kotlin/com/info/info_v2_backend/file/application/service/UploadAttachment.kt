package com.info.info_v2_backend.file.application.service

import com.info.info_v2_backend.common.file.dto.request.GenerateFileRequest
import com.info.info_v2_backend.common.file.dto.response.PresignedUrlResponse
import com.info.info_v2_backend.file.application.port.input.notice.UploadAttachmentUsecase
import com.info.info_v2_backend.file.application.port.output.UploadFilePort
import com.info.info_v2_backend.file.application.port.output.notice.RemoveAttachmentPort
import com.info.info_v2_backend.file.application.port.output.notice.SaveAttachmentPort
import com.info.info_v2_backend.file.domain.notice.Attachment
import com.info.info_v2_backend.file.domain.notice.AttachmentNotice
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class UploadAttachment(
    private val uploadFilePort: UploadFilePort,
    private val saveAttachmentPort: SaveAttachmentPort,
    private val removeAttachmentPort: RemoveAttachmentPort
): UploadAttachmentUsecase {

    override fun uploadAttachment(request: GenerateFileRequest, noticeId: String): PresignedUrlResponse {
        val fileId = UUID.randomUUID().toString()
        
        val dto = uploadFilePort.getPresignedUrl(request.fileName, request.contentType, "NOTICE/${noticeId}", "ATTACHMENT/${fileId}")
        val authUrl = dto.fileUrl
        dto.removeParameter()
        val attachment = Attachment(
            fileId,
            dto,
            AttachmentNotice(
                noticeId
            )
        )

        removeAttachmentPort.remove(noticeId)
        saveAttachmentPort.save(attachment)

        return PresignedUrlResponse(
            authUrl,
            dto.fileName
        )
    }


}