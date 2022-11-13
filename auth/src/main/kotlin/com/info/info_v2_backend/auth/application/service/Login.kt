package com.info.info_v2_backend.auth.application.service

import com.info.info_v2_backend.auth.adapter.input.rest.dto.request.LoginRequest
import com.info.info_v2_backend.auth.adapter.input.rest.dto.response.TokenResponse
import com.info.info_v2_backend.auth.application.port.input.LoginUsecase
import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.common.exception.ErrorCode
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class Login(
    private val authDetailsService: AuthDetailsService,
    private val passwordEncoder: PasswordEncoder,
    private val tokenProvider: TokenProvider
): LoginUsecase {

    override fun command(request: LoginRequest): TokenResponse {
        val userDetails = authDetailsService.loadUserByUsername(request.email)
        if (userDetails.password == passwordEncoder.encode(request.password)) return tokenProvider.encode(userDetails.username)
        else throw BusinessException("비밀번호가 올바르지 않습니다. -> ${request.password}", ErrorCode.INVALID_PASSWORD_ERROR)
    }

}