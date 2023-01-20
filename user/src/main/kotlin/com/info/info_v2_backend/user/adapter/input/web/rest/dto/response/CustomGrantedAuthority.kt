package com.info.info_v2_backend.user.adapter.input.web.rest.dto.response

import org.springframework.security.core.GrantedAuthority

open class CustomGrantedAuthority(
    val role: String
): GrantedAuthority {
    override fun getAuthority(): String {
        return this.role
    }

}