package com.info.info_v2_backend.company.adapter.output.rest

import com.info.info_v2_backend.common.auth.AuthenticationCodeDto
import com.info.info_v2_backend.common.auth.AuthenticationCodeType
import com.info.info_v2_backend.company.application.port.output.CheckEmailCodePort
import com.info.info_v2_backend.company.application.port.output.SaveContactorPort
import com.info.info_v2_backend.user.adapter.input.event.dto.ContactorDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@FeignClient(name = "AUTH-SERVICE")
interface AuthFeignClient: CheckEmailCodePort {

    @PostMapping
    override fun check(@RequestBody request: AuthenticationCodeDto): Boolean
}