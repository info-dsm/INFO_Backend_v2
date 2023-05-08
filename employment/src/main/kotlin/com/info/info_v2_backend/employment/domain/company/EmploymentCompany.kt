package com.info.info_v2_backend.employment.domain.company

import com.info.info_v2_backend.common.employment.EmploymentDto
import com.info.info_v2_backend.employment.adapter.input.rest.dto.response.AnonymousEmploymentListResponse
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class EmploymentCompany(
    companyNumber: String,
    companyName: String,
    companyLogo: String
) {
    @Column(name = "employment_company_number", nullable = false)
    val companyNumber: String = companyNumber

    @Column(name = "employment_company_name", nullable = false)
    val companyName: String = companyName

    @Column(name = "employment_company_logo", nullable = false, length = 3000)
    val companyLogo: String = companyLogo


    fun toEmploymentCompanyResponse(): EmploymentDto.EmploymentCompanyResponse {
        return EmploymentDto.EmploymentCompanyResponse(
            this.companyNumber,
        )
    }

    fun toAnonymousEmploymentCompanyResponse(): AnonymousEmploymentListResponse.AnonymousEmploymentResponse.AnonymousEmploymentCompanyResponse{
        return AnonymousEmploymentListResponse.AnonymousEmploymentResponse.AnonymousEmploymentCompanyResponse(
            this.companyName,
            this.companyLogo
        )
    }

}
