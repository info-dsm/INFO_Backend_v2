package com.info.info_v2_backend.file.domain.notice

import javax.persistence.Column

class AttachmentNotice(
    noticeId: String
) {
    @Column(name = "notice_id", nullable = false)
    val noticeId = noticeId

}