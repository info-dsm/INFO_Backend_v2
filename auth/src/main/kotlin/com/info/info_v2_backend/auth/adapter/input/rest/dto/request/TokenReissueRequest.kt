package com.info.info_v2_backend.auth.adapter.input.rest.dto.request

data class TokenReissueRequest (
    val accessToken: String,
    val refreshToken: String
)