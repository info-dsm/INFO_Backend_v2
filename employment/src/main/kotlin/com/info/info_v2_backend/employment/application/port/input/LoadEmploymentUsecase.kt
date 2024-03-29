package com.info.info_v2_backend.employment.application.port.input

import com.info.info_v2_backend.common.employment.EmploymentDto
import com.info.info_v2_backend.employment.adapter.input.rest.dto.response.AnonymousEmploymentListResponse
import com.info.info_v2_backend.employment.adapter.input.rest.dto.response.EveryGenerationClassInformationResponse

interface LoadEmploymentUsecase {

    fun loadCompanyEmploymentLine(companyNumber: String): List<EmploymentDto>
    fun loadCompanyConfirmedEmploymentLine(companyNumber: String): List<EmploymentDto>

    fun loadAdminEmploymentListResponse(classNum: Int, year: Int): AnonymousEmploymentListResponse
    fun loadAnonymousEmploymentListResponse(classNum: Int, year: Int): AnonymousEmploymentListResponse

    fun loadEveryGenerationClassInformationResponse(year: Int): EveryGenerationClassInformationResponse
}
