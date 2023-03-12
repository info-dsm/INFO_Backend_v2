package com.info.info_v2_backend.notice.adapter.output.rest

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(name = "APPLIES-SERVICE")
interface AppliesFeignClient: AppliesPort {

    @GetMapping("/count/{noticeId}")
    override fun loadEveryApplicantCount(@PathVariable noticeId: String): Int
}