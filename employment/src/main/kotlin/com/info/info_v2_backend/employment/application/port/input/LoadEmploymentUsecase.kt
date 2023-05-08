package com.info.info_v2_backend.employment.application.port.input

import com.info.info_v2_backend.common.employment.EmploymentDto
import com.info.info_v2_backend.employment.adapter.input.rest.dto.response.AnonymousEmploymentListResponse

interface LoadEmploymentUsecase {

    fun loadCompanyEmploymentLine(companyNumber: String): List<EmploymentDto>
    fun loadCompanyConfirmedEmploymentLine(companyNumber: String): List<EmploymentDto>

    fun loadAnonymousEmploymentListResponse(classNum: Int, year: Int): AnonymousEmploymentListResponse
}
