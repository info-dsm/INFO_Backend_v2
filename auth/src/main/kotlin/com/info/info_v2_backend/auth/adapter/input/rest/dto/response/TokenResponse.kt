package com.info.info_v2_backend.auth.adapter.input.rest.dto.response

data class TokenResponse (
    val accessToken: String,
    val refreshToken: String
)