package com.info.info_v2_backend.file.application.port.output.notice

import org.springframework.transaction.annotation.Transactional

interface RemoveAttachmentPort {

    @Transactional
    fun remove(noticeId: String)
}