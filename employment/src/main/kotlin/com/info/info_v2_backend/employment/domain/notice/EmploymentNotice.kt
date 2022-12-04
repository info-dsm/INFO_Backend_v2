package com.info.info_v2_backend.employment.domain.notice

import com.info.info_v2_backend.common.employment.EmploymentDto
import javax.persistence.Column
import javax.persistence.Embeddable


@Embeddable
class EmploymentNotice(
    noticeId: String
) {
    @Column(name = "noticeId", nullable = false)
    val noticeId: String = noticeId

    fun toEmploymentNoticeResponse(): EmploymentDto.EmploymentNoticeResponse {
        return EmploymentDto.EmploymentNoticeResponse(
            this.noticeId
        )
    }
}