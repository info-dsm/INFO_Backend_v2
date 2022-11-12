package com.info.info_v2_backend.auth.adapter.output.rest

import com.info.info_v2_backend.auth.application.port.output.LoadUserByUsernamePort
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(name = "USER-SERVICe")
interface UserFeignClient: LoadUserByUsernamePort {
    @GetMapping
    override fun loadUserByUsername(@RequestParam email: String): UserDetails
}