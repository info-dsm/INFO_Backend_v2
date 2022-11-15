package com.info.info_v2_backend.company.adapter.output.rest

import com.info.info_v2_backend.common.auth.AuthenticationCodeDto
import com.info.info_v2_backend.company.application.port.output.CheckEmailCodePort
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@FeignClient(name = "AUTH-SERVICE", fallbackFactory = AuthFeignClientFallbackFactory::class)
interface AuthFeignClient: CheckEmailCodePort {

    @PostMapping("/code")
    override fun check(@RequestBody request: AuthenticationCodeDto): Boolean
}