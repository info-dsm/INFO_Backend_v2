package com.info.info_v2_backend.user.adapter.input.event.dto

import com.info.info_v2_backend.user.domain.Role
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class CommonUserDetails(
    val roleList: MutableList<Role>,
    private val password: String,
    val userEmail: String
): UserDetails {


    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        var authorityList: MutableList<GrantedAuthority> = ArrayList()
        this.roleList.map{
            authorityList.add(SimpleGrantedAuthority(it.toString()))
        }
        return authorityList
    }

    override fun getPassword(): String {
        return this.password
    }

    override fun getUsername(): String {
        return this.userEmail
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