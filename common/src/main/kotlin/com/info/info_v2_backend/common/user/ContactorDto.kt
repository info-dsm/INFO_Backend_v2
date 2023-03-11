package com.info.info_v2_backend.common.user

class ContactorDto(
    name: String,
    email: String,
    val rank: String,
    val phoneNumber: String?,
    val passwordHint: String?,
    val companyNumber: String
): UserDto(
    name,
    email
)
