package com.info.info_v2_backend.notice.domain.support

import com.info.info_v2_backend.notice.adapter.input.rest.dto.request.pay.EditPayRequest
import com.info.info_v2_backend.notice.adapter.input.rest.dto.request.pay.EmploymentPayRequest
import com.info.info_v2_backend.notice.adapter.input.rest.dto.request.pay.PayRequest
import javax.persistence.Column
import javax.persistence.Embeddable
import javax.persistence.Id

@Embeddable
class Pay(
    fieldTrainingPayPerMonth: Long,
    yearPayStart: Long,
    yearPayEnd: Long,
) {

    @Id
    val id: Long? = null

    @Column(name = "field_training_pay", nullable = false)
    var fieldTrainingPayPerMonth: Long = fieldTrainingPayPerMonth

    @Column(name = "year_pay_start", nullable = false)
    var yearPayStart: Long = yearPayStart

    @Column(name = "year_pay_end", nullable = false)
    var yearPayEnd: Long = yearPayEnd

    fun editPay(r: EditPayRequest) {
        r.fieldTrainingPayPerMonth?.let {
            this.fieldTrainingPayPerMonth = r.fieldTrainingPayPerMonth
        }
        r.editFullTimeEmploymentPay?.let {
            it.yearPayStart?.let {
                this.yearPayStart = it
            }
            it.yearPayEnd?.let {
                this.yearPayEnd =it
            }
        }
    }

    fun toPayRequest(): PayRequest {
        return PayRequest(
            this.fieldTrainingPayPerMonth,
            EmploymentPayRequest(
                this.yearPayStart,
                this.yearPayEnd,
            )
        )
    }
}