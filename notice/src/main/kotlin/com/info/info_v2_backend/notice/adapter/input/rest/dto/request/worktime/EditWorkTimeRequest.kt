package com.info.info_v2_backend.notice.adapter.input.rest.dto.request.worktime

data class EditWorkTimeRequest(
    val commuteStartTime: Int?,
    val commuteEndTime: Int?,
    val workTimePerDay: Int?,
    val workTimePerWeek: Int?,
    val isFlexible: Boolean?

)
