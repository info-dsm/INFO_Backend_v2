package com.info.info_v2_backend.applies.adapter.output.rest

import com.info.info_v2_backend.applies.application.port.output.load.LoadStudentPort
import com.info.info_v2_backend.common.user.StudentDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping

@FeignClient(name = "USER-SERVICE", fallbackFactory = UserFeignClientFallback::class,)
interface UserFeignClient: LoadStudentPort {
    @GetMapping("/student")
    override fun loadStudent(studentEmail: String): StudentDto?

}