package com.info.info_v2_backend.notice.domain.openPeriod

import com.info.info_v2_backend.notice.adapter.input.rest.dto.request.NoticeOpenPeriodRequest
import java.time.LocalDate
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class NoticeOpenPeriod(
    startDate: LocalDate,
    endDate: LocalDate
) {

    @Column(name = "start_date", nullable = false)
    val startDate: LocalDate = startDate

    @Column(name = "end_date", nullable = false)
    val endDate: LocalDate = endDate

    fun toNoticeOpenPeriod(): NoticeOpenPeriodRequest {
        return NoticeOpenPeriodRequest(
            this.startDate,
            this.endDate
        )
    }

}