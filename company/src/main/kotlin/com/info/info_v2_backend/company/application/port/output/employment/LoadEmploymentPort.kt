package com.info.info_v2_backend.company.application.port.output.employment

import com.info.info_v2_backend.common.employment.EmploymentDto

interface LoadEmploymentPort {

    fun loadEmploymentList(companyNumber: String): List<EmploymentDto>
}