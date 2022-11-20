package com.info.info_v2_backend.file.application.port.input.notice

import com.info.info_v2_backend.common.file.dto.AttachmentResponse

interface LoadAttachmentUsecase {

    fun loadAttachmentList(noticeId: String): List<AttachmentResponse>
}