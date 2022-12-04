package com.info.info_v2_backend.auth.adapter.input.rest.dto.request


data class LoginCompanyRequest(
    val companyNumber: String,
    val password: String
)

