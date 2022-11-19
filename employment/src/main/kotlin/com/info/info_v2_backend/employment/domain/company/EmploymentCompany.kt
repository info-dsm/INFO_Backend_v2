package com.info.info_v2_backend.employment.domain.company

import com.info.info_v2_backend.employment.adapter.input.rest.dto.request.EmploymentResponse
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class EmploymentCompany(
    companyNumber: String,
    companyName: String
) {
    @Column(name = "employment_company_number", nullable = false)
    val companyNumber: String = companyNumber

    @Column(name = "employment_company_name", nullable = false)
    val companyName = companyName

    fun toEmploymentCompanyResponse(): EmploymentResponse.EmploymentCompanyResponse {
        return EmploymentResponse.EmploymentCompanyResponse(
            this.companyNumber,
            this.companyName
        )
    }

}