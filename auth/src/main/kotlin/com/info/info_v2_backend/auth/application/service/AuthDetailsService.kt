package com.info.info_v2_backend.auth.application.service

import com.info.info_v2_backend.auth.application.port.output.LoadUserByUsernamePort
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component

@Component
class AuthDetailsService(
    private val loadUserByUsernamePort: LoadUserByUsernamePort
): UserDetailsService {
    override fun loadUserByUsername(userEmail: String): UserDetails {
        return loadUserByUsernamePort.loadUserByUsername(userEmail)
    }

}