package com.info.info_v2_backend.file.application.service

import com.info.info_v2_backend.file.application.port.input.notice.RemoveAttachmentUsecase
import com.info.info_v2_backend.file.application.port.output.notice.RemoveAttachmentPort
import org.springframework.stereotype.Service

@Service
class RemoveAttachment(
    private val removeAttachmentPort: RemoveAttachmentPort
): RemoveAttachmentUsecase {
    override fun remove(noticeId: String) {
        removeAttachmentPort.remove(noticeId)
    }


}