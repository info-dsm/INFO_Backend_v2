package com.info.info_v2_backend.email.adapter.output.rest

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping

@FeignClient(url = "https://api.mailchannels.net/tx/v1/send")
interface CloudflareFeignClient {

    @PostMapping
    fun send()

}