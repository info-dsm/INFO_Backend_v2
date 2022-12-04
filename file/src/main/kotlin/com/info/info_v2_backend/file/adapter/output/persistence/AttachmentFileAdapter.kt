package com.info.info_v2_backend.file.adapter.output.persistence

import com.info.info_v2_backend.file.adapter.output.persistence.repository.AttachmentRepository
import com.info.info_v2_backend.file.application.port.output.notice.LoadAttachmentPort
import com.info.info_v2_backend.file.application.port.output.notice.RemoveAttachmentPort
import com.info.info_v2_backend.file.application.port.output.notice.SaveAttachmentPort
import com.info.info_v2_backend.file.domain.notice.Attachment
import com.info.info_v2_backend.file.domain.notice.AttachmentNotice
import org.springframework.stereotype.Service

@Service
class AttachmentFileAdapter(
    private val attachmentRepository: AttachmentRepository
): SaveAttachmentPort, RemoveAttachmentPort, LoadAttachmentPort {
    override fun save(attachment: Attachment) {
        attachmentRepository.save(attachment)
    }

    override fun remove(noticeId: String) {
        attachmentRepository.deleteByNotice(AttachmentNotice(noticeId))
    }

    override fun loadAttachment(noticeId: String): List<Attachment> {
        return attachmentRepository.findByNotice(AttachmentNotice(noticeId))
    }


}