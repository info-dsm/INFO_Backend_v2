package com.info.info_v2_backend.notice.adapter.output.rest

import com.info.info_v2_backend.common.company.CompanyDto
import com.info.info_v2_backend.notice.application.port.output.LoadCompanyPort
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(name = "COMPANY-SERVICE", fallbackFactory = CompanyFeignClientFallback::class)
interface CompanyFeignClient: LoadCompanyPort {

    @GetMapping("/dto/{companyNumber}")
    override fun loadCompany(@PathVariable companyNumber: String): CompanyDto?

    @GetMapping("/thumbnail/{companyNumber}")
    override fun loadCompanyThumbnailList(@PathVariable companyNumber: String): MutableList<String>

}