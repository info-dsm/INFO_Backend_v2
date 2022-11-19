package com.info.info_v2_backend.notice.adapter.input.rest.dto.request.pay

import javax.validation.constraints.Min


data class EmploymentPayRequest(
    @field:Min(0, message = "yearPayStart는 0 이상이어야합니다.")
    val yearPayStart: Long,
    @field:Min(0, message = "yearPayEnd는 0 이상이어야합니다.")
    val yearPayEnd: Long,
)
