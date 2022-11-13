package com.info.info_v2_backend.auth.application.service

import com.info.info_v2_backend.auth.application.port.output.LoadUserByEmailPort
import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.common.exception.ErrorCode
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component

@Component
class AuthDetailsService(
    private val loadUserByUsernamePort: LoadUserByEmailPort
): UserDetailsService {
    override fun loadUserByUsername(userEmail: String): UserDetails {
        return loadUserByUsernamePort.loadUserDetailsByUsername(userEmail)
    }

}