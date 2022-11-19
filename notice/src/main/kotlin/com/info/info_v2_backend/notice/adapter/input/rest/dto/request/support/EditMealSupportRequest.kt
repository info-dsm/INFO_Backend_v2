package com.info.info_v2_backend.notice.adapter.input.rest.dto.request.support

data class EditMealSupportRequest(
    val mealSupportPay: Long?,
    val breakfast: Boolean?,
    val lunch: Boolean?,
    val dinner: Boolean?

)
