package com.info.info_v2_backend.company.adapter.output.rest

import com.info.info_v2_backend.common.employment.EmploymentDto
import com.info.info_v2_backend.company.application.port.output.employment.LoadEmploymentPort
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(name = "EMPLOYMENT-SERVICE", fallbackFactory = EmploymentFeignClientFalllback::class)
interface EmploymentFeignClient: LoadEmploymentPort {

    @GetMapping("/{companyNumber}")
    override fun loadEmploymentList(@PathVariable companyNumber: String): List<EmploymentDto>
}