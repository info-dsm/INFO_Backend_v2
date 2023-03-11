package com.info.info_v2_backend.auth.adapter.output.rest

import com.info.info_v2_backend.common.user.ContactorDto
import com.info.info_v2_backend.user.adapter.input.web.rest.dto.response.CommonUserDetails
import org.slf4j.LoggerFactory
import org.springframework.cloud.openfeign.FallbackFactory
import org.springframework.stereotype.Component

@Component
class UserFeignClientFallback: FallbackFactory<UserFeignClient> {
    private val log = LoggerFactory.getLogger(this.javaClass)

    override fun create(cause: Throwable?): UserFeignClient {
        return object : UserFeignClient {
            override fun loadUserDetailsByUsername(userEmail: String): CommonUserDetails? {
                return null
            }

            override fun load(companyNumber: String): ContactorDto? {
                return null
            }

        }
    }
}