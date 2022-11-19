package com.info.info_v2_backend.common.employment

data class EmploymentDto (
    val id: String,
    val companyNumber: String,
    val studentEmail: String,
    val name: String,
    val generation: Int,
    val status: EmploymentStatusDto

) {
    enum class EmploymentStatusDto(
        val meaning: String
    ) {
        INTERN("인턴"),
        RECRUITMENT_CONFIRMED("채용전환확정"),
        RECRUITMENT_FAILED("채용전환실패")


    }
}