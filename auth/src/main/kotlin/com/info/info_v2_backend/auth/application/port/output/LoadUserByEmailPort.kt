package com.info.info_v2_backend.auth.application.port.output

import com.info.info_v2_backend.user.adapter.input.event.dto.CommonUserDetails
import org.springframework.security.core.userdetails.UserDetails

interface LoadUserByEmailPort {

    fun loadUserDetailsByUsername(email: String): CommonUserDetails
}