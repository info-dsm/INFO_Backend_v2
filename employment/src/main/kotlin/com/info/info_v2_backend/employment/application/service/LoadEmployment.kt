package com.info.info_v2_backend.employment.application.service

import com.info.info_v2_backend.employment.adapter.input.rest.dto.request.EmploymentResponse
import com.info.info_v2_backend.employment.application.port.input.LoadEmploymentUsecase
import com.info.info_v2_backend.employment.application.port.output.LoadEmploymentPort
import org.springframework.stereotype.Service

@Service
class LoadEmployment(
    private val loadEmploymentPort: LoadEmploymentPort
): LoadEmploymentUsecase {

    override fun loadCompanyEmploymentLine(companyNumber: String): List<EmploymentResponse> {
        return loadEmploymentPort.loadEmploymentByCompany(companyNumber).map {
            it.toEmploymentResponse()
        }
    }
}