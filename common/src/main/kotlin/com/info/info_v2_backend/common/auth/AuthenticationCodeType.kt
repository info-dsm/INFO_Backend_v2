package com.info.info_v2_backend.common.auth

enum class AuthenticationCodeType(
    val timeToLive: Long,
    val length: Int
) {
    SIGNUP_EMAIL(60 * 60 * 24, 4),
    CHANGE_EMAIL(60 * 60 * 24, 4),
    CHANGE_PASSWORD(60 * 60, 4),
    TEACHER(60 * 60 * 24, 4),

}