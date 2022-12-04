package com.info.info_v2_backend.auth.application.service

import com.info.info_v2_backend.auth.adapter.input.rest.dto.request.LoginCompanyRequest
import com.info.info_v2_backend.auth.adapter.input.rest.dto.request.LoginRequest
import com.info.info_v2_backend.auth.adapter.input.rest.dto.response.TokenResponse
import com.info.info_v2_backend.auth.application.port.input.LoginUsecase
import com.info.info_v2_backend.auth.application.port.output.LoadContactorPort
import com.info.info_v2_backend.auth.application.port.output.SaveCodePort
import com.info.info_v2_backend.auth.domain.Code
import com.info.info_v2_backend.common.auth.AuthenticationCodeType
import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.common.exception.ErrorCode
import com.info.info_v2_backend.user.adapter.input.web.rest.dto.response.CommonUserDetails
import com.info.info_v2_backend.user.domain.Role
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class Login(
    private val authDetailsService: AuthDetailsService,
    private val tokenProvider: TokenProvider,
    private val saveCodePort: SaveCodePort,
    private val encoder: PasswordEncoder,
    private val loadContactorPort: LoadContactorPort
): LoginUsecase {

    override fun loginUser(request: LoginRequest): TokenResponse {
        val userDetails = authDetailsService.loadUserByUsername(request.email)
        return login(request.password, userDetails)
    }

    override fun loginCompany(request: LoginCompanyRequest): TokenResponse {
        val userDetails = authDetailsService.loadUserByUsername(
            loadContactorPort.load(request.companyNumber)
                ?: throw BusinessException(errorCode = ErrorCode.NO_DATA_FOUND_ERROR)
        )
        return login(request.password, userDetails)
    }

    private fun login(password: String, userDetails: CommonUserDetails): TokenResponse {
        if (encoder.matches(password, userDetails.password)){
            val dto = tokenProvider.encode(userDetails.username, userDetails.getCompanyNumber(), userDetails.authorities.first().role)
            saveCodePort.save(
                Code(
                    userDetails.username,
                    dto.refreshToken,
                    AuthenticationCodeType.REFRESH.timeToLive,
                    AuthenticationCodeType.REFRESH
                )
            )

            return dto
        } else throw BusinessException("비밀번호가 올바르지 않습니다. -> ${password}", ErrorCode.INVALID_INPUT_DATA_ERROR)
    }

}