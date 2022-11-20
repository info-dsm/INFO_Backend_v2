package com.info.info_v2_backend.file.application.service

import com.info.info_v2_backend.file.application.port.input.notice.UploadAttachmentUsecase
import com.info.info_v2_backend.file.application.port.output.UploadFilePort
import com.info.info_v2_backend.file.application.port.output.notice.RemoveAttachmentPort
import com.info.info_v2_backend.file.application.port.output.notice.SaveAttachmentPort
import com.info.info_v2_backend.file.domain.notice.Attachment
import com.info.info_v2_backend.file.domain.notice.AttachmentNotice
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.util.*

@Service
class UploadAttachment(
    private val uploadFilePort: UploadFilePort,
    private val saveAttachmentPort: SaveAttachmentPort,
    private val removeAttachmentPort: RemoveAttachmentPort
): UploadAttachmentUsecase {

    @Async
    override fun uploadAttachment(attachment: MultipartFile, noticeId: String) {
        val fileId = UUID.randomUUID().toString()
        val dto = uploadFilePort.upload(attachment, "NOTICE/${noticeId}", "ATTACHMENT/${fileId}")
        val attachment = Attachment(
            fileId,
            dto,
            AttachmentNotice(
                noticeId
            )
        )

        removeAttachmentPort.remove(noticeId)
        saveAttachmentPort.save(attachment)

    }


}