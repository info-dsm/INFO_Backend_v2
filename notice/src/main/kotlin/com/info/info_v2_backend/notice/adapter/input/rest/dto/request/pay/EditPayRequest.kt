package com.info.info_v2_backend.notice.adapter.input.rest.dto.request.pay

data class EditPayRequest(
    val fieldTrainingPayPerMonth: Long?,
    val editFullTimeEmploymentPay: EditEmploymentPayRequest?

)