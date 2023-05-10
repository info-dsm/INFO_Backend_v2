package com.info.info_v2_backend.file.application.service.notice

import com.info.info_v2_backend.common.file.dto.response.AttachmentResponse
import com.info.info_v2_backend.file.application.port.output.notice.LoadAttachmentPort
import com.info.info_v2_backend.file.application.port.input.notice.LoadAttachmentUsecase
import org.springframework.stereotype.Service

@Service
class LoadAttachment(
    private val loadAttachmentPort: LoadAttachmentPort
): LoadAttachmentUsecase {
    override fun loadAttachmentList(noticeId: String): List<AttachmentResponse> {
        return loadAttachmentPort.loadAttachment(
            noticeId
        ).map {
            it.toAttachmentResponse()
        }
    }


}
