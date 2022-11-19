package com.info.info_v2_backend.email.adapter.output.rest

import com.info.info_v2_backend.email.application.port.output.LoadEmailUserPort
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam


@FeignClient(name = "USER-SERVICE", fallbackFactory = UserFiegnClientFallback::class,)
interface UserFeignClient: LoadEmailUserPort {

    @GetMapping("/exist")
    override fun loadEmailUser(@RequestParam(value = "userEmail") userEmail: String): String?

}