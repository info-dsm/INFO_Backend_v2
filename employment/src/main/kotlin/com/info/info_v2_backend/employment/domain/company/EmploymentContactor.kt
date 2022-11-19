package com.info.info_v2_backend.employment.domain.company

import com.info.info_v2_backend.employment.adapter.input.rest.dto.request.EmploymentResponse
import javax.persistence.Column

class EmploymentContactor(
    contactorEmail: String
) {
    @Column(name = "employment_contactor_email", nullable = false)
    val contactorEmail: String = contactorEmail

    fun toEmploymentContactorResponse(): EmploymentResponse.EmploymentContactorResponse {
        return EmploymentResponse.EmploymentContactorResponse(
            this.contactorEmail
        )
    }
}