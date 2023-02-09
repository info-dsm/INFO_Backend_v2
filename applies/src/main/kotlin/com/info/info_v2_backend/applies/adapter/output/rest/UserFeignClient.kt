package com.info.info_v2_backend.applies.adapter.output.rest

import com.info.info_v2_backend.applies.application.port.output.student.LoadStudentPort
import com.info.info_v2_backend.common.user.StudentDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(name = "USER-SERVICE", fallbackFactory = UserFeignClientFallback::class,)
interface UserFeignClient: LoadStudentPort {
    @GetMapping("/student")
    override fun loadStudent(@RequestParam studentEmail: String): StudentDto?

}