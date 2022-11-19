package com.info.info_v2_backend.applies.domain.user

import com.info.info_v2_backend.common.applies.AppliesDto
import com.info.info_v2_backend.common.user.Generation
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class Applicant(
    email: String,
    name: String,
    entranceYear: Int
) {
    @Column(name = "applicant_email", nullable = false)
    val email: String = email

    @Column(name = "applicant_name", nullable = false)
    val name: String = name

    @Column(name = "entrance_year", nullable = false)
    val entranceYear: Int = entranceYear

    fun toApplicantDto(): AppliesDto.ApplicantDto {
        return AppliesDto.ApplicantDto(
            this.email,
            this.name,
            entranceYear - Generation.FIRST_GENERATION_YEAR + 1
        )
    }

}