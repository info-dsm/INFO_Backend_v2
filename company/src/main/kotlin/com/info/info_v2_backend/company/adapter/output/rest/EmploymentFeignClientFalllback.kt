package com.info.info_v2_backend.company.adapter.output.rest

import com.info.info_v2_backend.common.employment.EmploymentDto
import org.springframework.cloud.openfeign.FallbackFactory
import org.springframework.stereotype.Component

@Component
class EmploymentFeignClientFalllback: FallbackFactory<EmploymentFeignClient> {

    override fun create(cause: Throwable?): EmploymentFeignClient {
        return object : EmploymentFeignClient {
            override fun loadEmploymentList(companyNumber: String): List<EmploymentDto> {
                return ArrayList()
            }

        }
    }


}