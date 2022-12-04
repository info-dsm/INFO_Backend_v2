package com.info.info_v2_backend.employment.application.service

import com.info.info_v2_backend.common.employment.EmploymentDto
import com.info.info_v2_backend.employment.application.port.input.LoadEmploymentUsecase
import com.info.info_v2_backend.employment.application.port.output.LoadEmploymentPort
import org.springframework.stereotype.Service

@Service
class LoadEmployment(
    private val loadEmploymentPort: LoadEmploymentPort
): LoadEmploymentUsecase {

    override fun loadCompanyEmploymentLine(companyNumber: String): List<EmploymentDto> {
        return loadEmploymentPort.loadEmploymentByCompany(companyNumber).map {
            it.toEmploymentResponse()
        }
    }

    override fun loadCompanyConfirmedEmploymentLine(companyId: String): List<EmploymentDto> {
        return loadEmploymentPort.loadConfirmedEmploymentByCompany(companyId).map {
            it.toEmploymentResponse()
        }
    }
}