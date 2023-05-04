package com.info.info_v2_backend.employment.adapter.output.rest

import com.info.info_v2_backend.common.applies.AppliesDto
import com.info.info_v2_backend.common.user.StudentDto
import com.info.info_v2_backend.employment.application.port.output.LoadUserPort
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(name = "USER-SERVICE", fallbackFactory = UserFeignClientFallback::class)
interface UserFeignClient: LoadUserPort {

    @GetMapping("/student")
    override fun loadStudent(@RequestParam studentEmail: String): StudentDto?

}
