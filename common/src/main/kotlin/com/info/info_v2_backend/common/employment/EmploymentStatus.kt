package com.info.info_v2_backend.common.employment

enum class EmploymentStatus(
    val meaning: String
) {
    INTERN("인턴"),
    RECRUITMENT_CONFIRMED("채용전환확정"),
    RECRUITMENT_FAILED("채용전환실패")


}