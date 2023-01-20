package com.info.info_v2_backend.user.adapter.input.web.rest.dto.response

import com.info.info_v2_backend.common.user.UserDto

class ContactorResponse(
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
