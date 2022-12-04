package com.info.info_v2_backend.auth.adapter.output.rest

import org.springframework.cloud.openfeign.FallbackFactory
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service

@Component
class CompanyFeignClientFallbackFactory: FallbackFactory<CompanyFeignClient> {
    override fun create(cause: Throwable?): CompanyFeignClient {
        return object : CompanyFeignClient {
            override fun load(companyNumber: String): String? {
                return null
            }
        }
    }
}