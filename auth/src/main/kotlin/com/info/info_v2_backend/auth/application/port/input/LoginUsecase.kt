package com.info.info_v2_backend.auth.application.port.input

import com.info.info_v2_backend.auth.adapter.input.rest.dto.request.LoginCompanyRequest
import com.info.info_v2_backend.auth.adapter.input.rest.dto.request.LoginRequest
import com.info.info_v2_backend.auth.adapter.input.rest.dto.response.TokenResponse

interface LoginUsecase {

    fun loginUser(request: LoginRequest): TokenResponse
    fun loginCompany(request: LoginCompanyRequest): TokenResponse
}