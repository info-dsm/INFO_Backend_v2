package com.info.info_v2_backend.employment.application.port.input

import com.info.info_v2_backend.employment.adapter.input.rest.dto.request.EmploymentResponse

interface LoadEmploymentUsecase {

    fun loadCompanyEmploymentLine(companyId: String): List<EmploymentResponse>
}