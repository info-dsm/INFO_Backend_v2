package com.info.info_v2_backend.employment.domain.notice

import com.info.info_v2_backend.employment.adapter.input.rest.dto.request.EmploymentResponse
import javax.persistence.Column
import javax.persistence.Embeddable


@Embeddable
class EmploymentNotice(
    noticeId: String
) {
    @Column(name = "noticeId", nullable = false)
    val noticeId: String = noticeId

    fun toEmploymentNoticeResponse(): EmploymentResponse.EmploymentNoticeResponse {
        return EmploymentResponse.EmploymentNoticeResponse(
            this.noticeId
        )
    }
}