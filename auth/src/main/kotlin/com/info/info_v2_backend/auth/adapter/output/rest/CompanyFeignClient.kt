package com.info.info_v2_backend.auth.adapter.output.rest

import com.info.info_v2_backend.auth.application.port.output.LoadContactorPort
import com.info.info_v2_backend.user.adapter.input.web.rest.dto.response.CommonUserDetails
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable


@FeignClient(name = "COMPANY-SERVICE", fallbackFactory = CompanyFeignClientFallbackFactory::class)
interface CompanyFeignClient: LoadContactorPort {

    @GetMapping("/{companyNumber}/contactor")
    override fun load(@PathVariable companyNumber: String): String?

}