package com.info.info_v2_backend.employment.domain.company

import com.info.info_v2_backend.common.employment.EmploymentDto
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class EmploymentCompany(
    companyNumber: String,
) {
    @Column(name = "employment_company_number", nullable = false)
    val companyNumber: String = companyNumber


    fun toEmploymentCompanyResponse(): EmploymentDto.EmploymentCompanyResponse {
        return EmploymentDto.EmploymentCompanyResponse(
            this.companyNumber,
        )
    }

    override fun equals(other: Any?): Boolean {
        if (other is EmploymentCompany) return this.companyNumber == other.companyNumber
        return super.equals(other)
    }
}