package com.info.info_v2_backend.auth.adapter.output.rest

import com.info.info_v2_backend.auth.application.port.output.userFeignPort.UserFeignPort
import com.info.info_v2_backend.user.adapter.input.web.rest.dto.response.CommonUserDetails
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(name = "USER-SERVICE", fallbackFactory = UserFeignClientFallback::class,)
interface UserFeignClient: UserFeignPort {
    @GetMapping
    override fun loadUserDetailsByUsername(
        @RequestParam userEmail: String
    ): CommonUserDetails?

    @PutMapping("/password")
    override fun change(@RequestParam email: String, @RequestParam password: String)

}