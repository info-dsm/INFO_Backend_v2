package com.info.info_v2_backend.employment.application.port.input

import com.info.info_v2_backend.common.employment.EmploymentDto

interface LoadEmploymentUsecase {

    fun loadCompanyEmploymentLine(companyId: String): List<EmploymentDto>
    fun loadCompanyConfirmedEmploymentLine(companyId: String): List<EmploymentDto>
}