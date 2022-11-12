package com.info.info_v2_backend.auth.adapter.input.rest.dto.request

data class LoginRequest (
    val email: String,
    val password: String
)