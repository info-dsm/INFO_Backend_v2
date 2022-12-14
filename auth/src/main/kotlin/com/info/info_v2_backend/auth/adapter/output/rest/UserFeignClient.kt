package com.info.info_v2_backend.auth.adapter.output.rest

import com.info.info_v2_backend.auth.application.port.output.LoadUserDetailsPort
import com.info.info_v2_backend.user.adapter.input.web.rest.dto.response.CommonUserDetails
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(name = "USER-SERVICE", fallbackFactory = UserFeignClientFallback::class,)
interface UserFeignClient: LoadUserDetailsPort {
    @GetMapping
    override fun loadUserDetailsByUsername(
        @RequestParam userEmail: String
    ): CommonUserDetails?


}