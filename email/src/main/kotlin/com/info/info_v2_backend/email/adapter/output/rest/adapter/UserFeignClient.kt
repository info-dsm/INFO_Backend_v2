package com.info.info_v2_backend.email.adapter.output.rest.adapter

import com.info.info_v2_backend.common.user.UserEmailIdDto
import com.info.info_v2_backend.email.application.port.output.LoadEmailUserPort
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping


@FeignClient(name = "USER-SERVICE", fallbackFactory = UserFiegnClientFallback::class)
interface UserFeignClient: LoadEmailUserPort {

    @PostMapping
    override fun loadEmailUser(userEmail: String): UserEmailIdDto

}