package com.info.info_v2_backend.notice.adapter.input.rest.dto.request

import com.info.info_v2_backend.notice.domain.openPeriod.NoticeOpenPeriod
import java.time.LocalDate

data class NoticeOpenPeriodRequest (
    val startDate: LocalDate,
    val endDate: LocalDate
) {
    fun toNoticeOpenPeriod(): NoticeOpenPeriod {
        return NoticeOpenPeriod(
            this.startDate,
            this.endDate
        )
    }
}