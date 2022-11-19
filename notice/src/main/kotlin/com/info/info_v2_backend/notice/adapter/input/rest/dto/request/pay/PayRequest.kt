package com.info.info_v2_backend.notice.adapter.input.rest.dto.request.pay

import com.info.info_v2_backend.notice.domain.support.Pay
import javax.validation.Valid


data class PayRequest(
    val fieldTrainingPayPerMonth: Long,
    @field:Valid
    val fullTimeEmploymentPay: EmploymentPayRequest
) {
    fun toPay(): Pay {
        return Pay(
            this.fieldTrainingPayPerMonth,
            this.fullTimeEmploymentPay.yearPayStart,
            this.fullTimeEmploymentPay.yearPayEnd,
        )
    }
}
