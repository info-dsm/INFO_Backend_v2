package com.info.info_v2_backend.email.adapter.output.rest

import com.info.info_v2_backend.email.adapter.output.rest.dto.CloudflareMailDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@FeignClient(name = "cloudflare", url = "https://api.mailchannels.net/tx/v1/send")
interface CloudflareFeignClient {

    @PostMapping
    fun send(@RequestBody request: CloudflareMailDto)
}