package com.info.info_v2_backend.file.application.service.notice

import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.common.exception.ErrorCode
import com.info.info_v2_backend.common.file.dto.request.GenerateFileRequest
import com.info.info_v2_backend.common.file.dto.response.PresignedUrlResponse
import com.info.info_v2_backend.file.application.port.input.UploadFileUsecase
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
    private val saveAttachmentPort: SaveAttachmentPort,
    private val uploadFileUsecase: UploadFileUsecase
): UploadAttachmentUsecase {

    override fun uploadAttachment(request: GenerateFileRequest, noticeId: String): PresignedUrlResponse {
        val dto = uploadFileUsecase.upload(request, "NOTICE/${noticeId}", "ATTACHMENT")

        val attachment = Attachment(
            dto,
            AttachmentNotice(
                noticeId
            )
        )

        saveAttachmentPort.save(attachment)

        return PresignedUrlResponse(
            dto.authenticatedUri?: throw BusinessException(errorCode = ErrorCode.UNDEFINED_ERROR),
            dto.fileName
        )
    }


}
