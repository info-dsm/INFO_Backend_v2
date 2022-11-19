package com.info.info_v2_backend.notice.adapter.output.rest

import com.info.info_v2_backend.common.company.CompanyDto
import org.springframework.cloud.openfeign.FallbackFactory
import org.springframework.stereotype.Component

@Component
class CompanyFeignClientFallback: FallbackFactory<CompanyFeignClient> {

    override fun create(cause: Throwable?): CompanyFeignClient {
        return object : CompanyFeignClient {
            override fun loadCompany(companyNumber: String): CompanyDto? {
                return null
            }

        }
    }
}