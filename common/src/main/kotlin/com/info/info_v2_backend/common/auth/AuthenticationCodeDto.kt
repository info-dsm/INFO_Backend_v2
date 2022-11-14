package com.info.info_v2_backend.common.auth

data class AuthenticationCodeDto(
    val email: String,
    val data: String,
    val type: AuthenticationCodeType
) {

}