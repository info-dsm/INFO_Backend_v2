package com.info.info_v2_backend.auth.adapter.input.rest.dto.request

import com.info.info_v2_backend.auth.adapter.input.rest.vaildation.SchoolEmail
import javax.validation.constraints.NotEmpty


data class LoginRequest (
    @field:NotEmpty
    val email: String,
    @field:NotEmpty
    val password: String
)

