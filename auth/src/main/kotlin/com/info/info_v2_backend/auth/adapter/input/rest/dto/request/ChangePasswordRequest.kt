package com.info.info_v2_backend.auth.adapter.input.rest.dto.request

data class ChangePasswordRequest(
    val email: String,
    val code: String,
    val newPassword: String
)
