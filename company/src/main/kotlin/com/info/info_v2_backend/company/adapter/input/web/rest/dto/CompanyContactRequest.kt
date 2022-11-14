package com.info.info_v2_backend.company.adapter.input.web.rest.dto

import com.info.info_v2_backend.company.domain.ContactorId
import javax.validation.Valid
import javax.validation.constraints.Email
import javax.validation.constraints.Pattern


data class CompanyContactRequest(
    val contactorName: String,
    val contactorRank: String,
    @field:Pattern(
        regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}\$",
        message = "전화번호는 반드시 02 or 0xx-xxxx-xxxx 조합이여야합니다."
    )
    val companyPhone: String,
    @field:Pattern(
        regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}\$",
        message = "전화번호는 반드시 02 or 0xx-xxxx-xxxx 조합이여야합니다."
    )
    val contactorPhone: String?,
    @field:Email(message = "올바른 이메일 형식이 아닙니다.")
    val email: String,
    @field:Valid
    @field:Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@\$%^&*-]).{8,30}\$",
        message = "비밀번호는 영소문자,대문자,숫자,특수문자 8~30자여야 합니다.")
    val password: String,

    val passwordHint: String
) {

    fun toContactorId(): ContactorId {
        return ContactorId(
            this.email
        )
    }
}
