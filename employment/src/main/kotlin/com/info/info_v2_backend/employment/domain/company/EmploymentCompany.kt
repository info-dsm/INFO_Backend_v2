package com.info.info_v2_backend.employment.domain.company

import com.info.info_v2_backend.employment.adapter.input.rest.dto.request.EmploymentResponse
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class EmploymentCompany(
    companyNumber: String,
) {
    @Column(name = "employment_company_number", nullable = false)
    val companyNumber: String = companyNumber


    fun toEmploymentCompanyResponse(): EmploymentResponse.EmploymentCompanyResponse {
        return EmploymentResponse.EmploymentCompanyResponse(
            this.companyNumber,
        )
    }

}