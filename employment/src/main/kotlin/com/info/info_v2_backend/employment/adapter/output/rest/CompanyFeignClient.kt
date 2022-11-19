package com.info.info_v2_backend.employment.adapter.output.rest

import com.info.info_v2_backend.common.company.CompanyDto
import com.info.info_v2_backend.employment.application.port.output.LoadCompanyPort
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(name = "COMPANY-SERVICE", fallbackFactory = CompanyFeignClientFallback::class)
interface CompanyFeignClient: LoadCompanyPort {

    @GetMapping("/dto/{companyNumber}")
    override fun loadCompany(@PathVariable companyNumber: String): CompanyDto?
}