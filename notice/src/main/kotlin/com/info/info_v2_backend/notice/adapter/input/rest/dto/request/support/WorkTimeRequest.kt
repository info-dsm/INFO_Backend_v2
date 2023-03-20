package com.info.info_v2_backend.notice.adapter.input.rest.dto.request.support

import com.info.info_v2_backend.notice.domain.support.WorkTime
import javax.validation.constraints.Max
import javax.validation.constraints.Min

data class WorkTimeRequest(
    @field:Min(0, message ="CommuteTime은 0~24까지입니다.")
    @field:Max(24, message = "CommuteTime은 0~24까지입니다.")
    val commuteStartTime: Int?,
    val commuteStartTimeMinute: Int?,
    @field:Min(0, message ="CommuteTime은 0~24까지입니다.")
    @field:Max(24, message = "CommuteTime은 0~24까지입니다.")
    val commuteEndTime: Int?,
    val commuteEndTimeMinute: Int?,
    val workTimePerDay: Int,
    val workTimePerWeek: Int,
    val isFlexible: Boolean

) {

    fun toWorkTime(): WorkTime {
        return WorkTime(
            this.commuteStartTime,
            this.commuteStartTimeMinute,
            this.commuteEndTime,
            this.commuteEndTimeMinute,
            this.workTimePerDay,
            this.workTimePerWeek,
            this.isFlexible
        )
    }

}
