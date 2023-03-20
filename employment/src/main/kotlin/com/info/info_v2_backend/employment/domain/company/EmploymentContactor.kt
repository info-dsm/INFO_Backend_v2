package com.info.info_v2_backend.employment.domain.company

import com.info.info_v2_backend.common.employment.EmploymentDto
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class EmploymentContactor(
    contactorEmail: String
) {
    @Column(name = "employment_contactor_email", nullable = false)
    val contactorEmail: String = contactorEmail

    fun toEmploymentContactorResponse(): EmploymentDto.EmploymentContactorResponse {
        return EmploymentDto.EmploymentContactorResponse(
            this.contactorEmail
        )
    }
}