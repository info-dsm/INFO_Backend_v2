package com.info.info_v2_backend.company.adapter.input.web.rest.dto.request.edit

import javax.validation.Valid
import javax.validation.constraints.Email
import javax.validation.constraints.Pattern

data class EditContactorRequest (
    val contactorName: String,
    val contactorRank: String,
    @field:Pattern(
        regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}\$",
        message = "전화번호는 반드시 02 or 0xx-xxxx-xxxx 조합이여야합니다."
    )
    val contactorPhone: String?,

    val passwordHint: String
)