package com.info.info_v2_backend.file.application.port.output.notice

import com.info.info_v2_backend.file.domain.notice.Attachment

interface SaveAttachmentPort {

    fun save(attachment: Attachment)
}