package com.info.info_v2_backend.company.adapter.output.rest

import com.info.info_v2_backend.common.auth.AuthenticationCodeDto
import org.springframework.cloud.openfeign.FallbackFactory
import org.springframework.cloud.openfeign.FeignClientFactoryBean
import org.springframework.stereotype.Component

@Component
class AuthFeignClientFallback: FallbackFactory<AuthFeignClient> {
    override fun create(cause: Throwable): AuthFeignClient {
        return object : AuthFeignClient {
            override fun check(request: AuthenticationCodeDto): Boolean {
                return false
            }
        }
    }

}