package com.info.info_v2_backend.user.adapter.input.web.rest.dto.response

import org.springframework.security.core.userdetails.UserDetails

class CommonUserDetails(
    private val password: String,
    private val username: String,
    private val authorities: List<CustomGrantedAuthority>,
    private val companyNumber: String?
): UserDetails {

    fun getCompanyNumber(): String? {
        return this.companyNumber
    }

    override fun getAuthorities(): List<CustomGrantedAuthority> {
        return this.authorities
    }

    override fun getPassword(): String {
        return this.password
    }

    override fun getUsername(): String {
        return this.username
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }

}