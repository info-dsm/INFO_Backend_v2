package com.info.info_v2_backend.file.domain.applies

import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class ResumeNotice(
    noticeId: String
){
    @Column(name = "notice_id", nullable = false)
    val noticeId: String = noticeId

}