package com.info.info_v2_backend.notice.adapter.input.rest.dto.request.support

import com.info.info_v2_backend.notice.domain.support.WorkTime
import javax.validation.constraints.Max
import javax.validation.constraints.Min

data class WorkTimeRequest(
    @field:Min(0, message ="CommuteTime은 0~24까지입니다.")
    @field:Max(24, message = "CommuteTime은 0~24까지입니다.")
    val commuteStartTime: Int?,
    @field:Min(0, message ="CommuteTime은 0~24까지입니다.")
    @field:Max(24, message = "CommuteTime은 0~24까지입니다.")
    val commuteEndTime: Int?,
    val isFlexible: Boolean

) {

    fun toWorkTime(): WorkTime {
        return WorkTime(
            this.commuteStartTime,
            this.commuteEndTime,
            this.isFlexible
        )
    }

}
