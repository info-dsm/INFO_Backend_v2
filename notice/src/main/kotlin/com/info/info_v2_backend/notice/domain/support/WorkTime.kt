package com.info.info_v2_backend.notice.domain.support

import com.info.info_v2_backend.notice.adapter.input.rest.dto.request.support.WorkTimeRequest
import com.info.info_v2_backend.notice.adapter.input.rest.dto.request.worktime.EditWorkTimeRequest
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class WorkTime(
    untilCommuteStartTime: Int,
) {
    @Column(name = "until_commute_start_time", nullable = false)
    var untilCommuteStartTime: Int = untilCommuteStartTime
        protected set


    fun toWorkTimeRequest(): WorkTimeRequest {
        return WorkTimeRequest(
            this.untilCommuteStartTime,
        )
    }

    fun editWorkTime(r: EditWorkTimeRequest) {
        r.untilCommuteStartTime?.let {
            this.untilCommuteStartTime = r.untilCommuteStartTime
        }
    }
}