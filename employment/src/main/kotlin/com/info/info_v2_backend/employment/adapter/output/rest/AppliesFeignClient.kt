package com.info.info_v2_backend.employment.adapter.output.rest

import com.info.info_v2_backend.common.applies.AppliesDto
import com.info.info_v2_backend.employment.application.port.output.LoadAppliesStudentPort
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(name = "APPLIES-SERVICE", fallbackFactory = AppliesFeignClientFallback::class)
interface AppliesFeignClient: LoadAppliesStudentPort {

    @GetMapping("/{noticeId}")
    override fun loadAppliesStudent(
        @PathVariable noticeId: String,
        @RequestParam studentEmail: String
    ): AppliesDto?

}