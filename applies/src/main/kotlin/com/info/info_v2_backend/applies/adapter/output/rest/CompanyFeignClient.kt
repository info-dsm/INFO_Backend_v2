package com.info.info_v2_backend.applies.adapter.output.rest

import com.info.info_v2_backend.applies.application.port.output.company.LoadCompanyPort
import com.info.info_v2_backend.common.company.CompanyDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(name = "COMPANY-SERVICE", fallbackFactory = CompanyFeignClientFallbackFactory::class)
interface CompanyFeignClient: LoadCompanyPort {

    @GetMapping("/dto/{companyNumber}")
    override fun loadCompany(@PathVariable companyNumber: String): CompanyDto?
}