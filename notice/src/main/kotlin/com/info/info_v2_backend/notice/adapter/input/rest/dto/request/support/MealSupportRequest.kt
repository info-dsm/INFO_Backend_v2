package com.info.info_v2_backend.notice.adapter.input.rest.dto.request.support

import com.info.info_v2_backend.notice.domain.support.MealSupport
import javax.validation.constraints.Min

data class MealSupportRequest(
    @field:Min(0, message = "mealSupportPay는 0 이상이어야 합니다.")
    var mealSupportPay: Long,
    var breakfast: Boolean,
    var lunch: Boolean,
    var dinner: Boolean
) {
    fun toMealSupport(): MealSupport {
        return MealSupport(
            this.mealSupportPay,
            this.breakfast,
            this.lunch,
            this.dinner
        )
    }

}
