package com.info.info_v2_backend.auth.application.service

import com.info.info_v2_backend.auth.adapter.input.rest.dto.request.LoginRequest
import com.info.info_v2_backend.auth.adapter.input.rest.dto.response.TokenResponse
import com.info.info_v2_backend.auth.application.port.input.LoginUsecase
import com.info.info_v2_backend.auth.application.port.output.SaveCodePort
import com.info.info_v2_backend.auth.domain.Code
import com.info.info_v2_backend.common.auth.AuthenticationCodeType
import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.common.exception.ErrorCode
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class Login(
    private val authDetailsService: AuthDetailsService,
    private val tokenProvider: TokenProvider,
    private val saveCodePort: SaveCodePort,
    private val encoder: PasswordEncoder
): LoginUsecase {

    override fun command(request: LoginRequest): TokenResponse {
        val userDetails = authDetailsService.loadUserByUsername(request.email)
        if (encoder.matches(request.password, userDetails.password)){
            val dto = tokenProvider.encode(userDetails.username)
            saveCodePort.save(
                Code(
                    userDetails.username,
                    dto.refreshToken,
                    AuthenticationCodeType.REFRESH.timeToLive,
                    AuthenticationCodeType.REFRESH
                )
            )

            return dto
        } else throw BusinessException("비밀번호가 올바르지 않습니다. -> ${request.password}", ErrorCode.INVALID_INPUT_DATA_ERROR)
    }

}