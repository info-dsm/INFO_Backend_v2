package com.info.info_v2_backend.notice.domain.support

import com.info.info_v2_backend.notice.adapter.input.rest.dto.request.support.EditMealSupportRequest
import com.info.info_v2_backend.notice.adapter.input.rest.dto.request.support.MealSupportRequest
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class MealSupport(
    @Column(name = "meal_support_pay")
    var mealSupportPay: Long,
    @Column(name = "breakfast_meal_support")
    var breakfast: Boolean,
    @Column(name = "lunch_meal_support")
    var lunch: Boolean,
    @Column(name = "dinner_meal_support")
    var dinner: Boolean
) {

    fun toMealSupportRequest(): MealSupportRequest {
        return MealSupportRequest(
            this.mealSupportPay,
            this.breakfast,
            this.lunch,
            this.dinner
        )
    }

    fun editMealSupport(r: EditMealSupportRequest) {
        r.breakfast?.let {
            this.breakfast = r.breakfast
        }
        r.lunch?.let {
            this.lunch = r.lunch
        }
        r.dinner?.let {
            this.dinner = r.dinner
        }
        r.mealSupportPay?.let {
            this.mealSupportPay = r.mealSupportPay
        }
    }
}