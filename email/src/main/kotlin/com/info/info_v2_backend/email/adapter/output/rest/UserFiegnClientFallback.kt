package com.info.info_v2_backend.email.adapter.output.rest

import org.slf4j.LoggerFactory
import org.springframework.cloud.openfeign.FallbackFactory
import org.springframework.stereotype.Component

@Component
class UserFiegnClientFallback: FallbackFactory<UserFeignClient> {
    private val log = LoggerFactory.getLogger(this.javaClass)

    override fun create(cause: Throwable?): UserFeignClient {
        return object : UserFeignClient {

            override fun loadEmailUser(userEmail: String): String {
                return userEmail
            }
        }
    }
}