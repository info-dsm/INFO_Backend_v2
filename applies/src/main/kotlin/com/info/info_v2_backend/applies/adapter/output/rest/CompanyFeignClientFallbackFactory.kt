package com.info.info_v2_backend.applies.adapter.output.rest

import com.info.info_v2_backend.common.company.CompanyDto
import org.springframework.cloud.openfeign.FallbackFactory


class CompanyFeignClientFallbackFactory: FallbackFactory<CompanyFeignClient> {
    override fun create(cause: Throwable?): CompanyFeignClient {
        return object : CompanyFeignClient{
            override fun loadCompany(companyNumber: String): CompanyDto? {
                return null
            }

        }
    }
}