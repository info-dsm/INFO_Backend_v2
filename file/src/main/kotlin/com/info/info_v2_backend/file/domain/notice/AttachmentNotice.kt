package com.info.info_v2_backend.file.domain.notice

import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class AttachmentNotice(
    noticeId: String
) {
    @Column(name = "notice_id", nullable = false)
    val noticeId = noticeId

}