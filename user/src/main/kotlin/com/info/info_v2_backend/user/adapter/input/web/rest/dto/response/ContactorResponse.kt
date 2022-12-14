package com.info.info_v2_backend.user.adapter.input.web.rest.dto.response

data class ContactorResponse(
    val name: String,
    val email: String,
    val rank: String,
    val phoneNumber: String?,
    val passwordHint: String?,
    val companyNumber: String
)
