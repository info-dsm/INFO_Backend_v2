package com.info.info_v2_backend.common.applies

import java.time.LocalDate

data class AppliesDto (
    val id: String,
    val applicant: ApplicantDto,
    val notice: AppliesNoticeDto,
    val status: AppliesStatus,
    val applicatedDate: LocalDate

) {
    data class ApplicantDto(
        val email: String,
        val name: String,
        val generation: Int
    )

    data class AppliesNoticeDto(
        val noticeId: String
    )

}