package com.info.info_v2_backend.company.adapter.output.rest

import com.info.info_v2_backend.user.adapter.input.web.rest.dto.response.ContactorResponse
import org.springframework.cloud.openfeign.FallbackFactory
import org.springframework.stereotype.Service

@Service
class UserFeingClientFallback: FallbackFactory<UserFeignClient> {
    override fun create(cause: Throwable?): UserFeignClient {
        return object : UserFeignClient {
            override fun loadContactor(companyNumber: String): ContactorResponse? {
                return null
            }
        }
    }
}