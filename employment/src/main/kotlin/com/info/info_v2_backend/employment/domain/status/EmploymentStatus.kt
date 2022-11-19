package com.info.info_v2_backend.employment.domain.status

enum class EmploymentStatus(
    val meaning: String
) {
    INTERN("인턴"),
    RECRUITMENT_CONFIRMED("채용전환"),
    RECRUITMENT_FAILED("채용전환실패")


}