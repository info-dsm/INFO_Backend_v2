package com.info.info_v2_backend.auth.application.port.output

import org.springframework.security.core.userdetails.UserDetails

interface LoadUserByUsernamePort {

    fun loadUserByUsername(email: String): UserDetails
}