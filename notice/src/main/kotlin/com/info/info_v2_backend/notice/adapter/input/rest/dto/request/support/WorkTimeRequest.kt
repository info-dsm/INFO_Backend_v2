package com.info.info_v2_backend.notice.adapter.input.rest.dto.request.support

import com.info.info_v2_backend.notice.domain.support.WorkTime
import javax.validation.constraints.Max
import javax.validation.constraints.Min

data class WorkTimeRequest(
    @field:Min(0, message ="CommuteTime은 0~24까지입니다.")
    @field:Max(24, message = "CommuteTime은 0~24까지입니다.")
    val untilCommuteStartTime: Int

) {

    fun toWorkTime(): WorkTime {
        return WorkTime(
            this.untilCommuteStartTime,
        )
    }

}
