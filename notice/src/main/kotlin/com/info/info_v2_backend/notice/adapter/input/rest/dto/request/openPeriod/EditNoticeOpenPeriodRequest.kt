package com.info.info_v2_backend.notice.adapter.input.rest.dto.request.openPeriod

import java.time.LocalDate

data class EditNoticeOpenPeriodRequest(
    val startDate: LocalDate?,
    val endDate: LocalDate?
)