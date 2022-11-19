package com.info.info_v2_backend.notice.adapter.input.rest.dto.request.worktime

data class EditWorkTimeRequest(
    val untilCommuteStartTime: Int?,
    val untilCommuteEndTime: Int?,
    val workTimeForWeek: Int?
)
