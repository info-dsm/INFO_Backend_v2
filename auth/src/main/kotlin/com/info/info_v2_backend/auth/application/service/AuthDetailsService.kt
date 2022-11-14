package com.info.info_v2_backend.auth.application.service

import com.info.info_v2_backend.auth.application.port.output.UserServicePort
import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.common.exception.ErrorCode
import com.info.info_v2_backend.user.adapter.input.web.rest.dto.response.CommonUserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component

@Component
class AuthDetailsService(
    private val loadUserByUsernamePort: UserServicePort
): UserDetailsService {
    override fun loadUserByUsername(userEmail: String): CommonUserDetails {
        return loadUserByUsernamePort.loadUserDetailsByUsername(userEmail)?: throw BusinessException("사용자를 찾지 못했습니다. -> ${userEmail}", ErrorCode.PERSISTENCE_DATA_NOT_FOUND_ERROR)
    }

}