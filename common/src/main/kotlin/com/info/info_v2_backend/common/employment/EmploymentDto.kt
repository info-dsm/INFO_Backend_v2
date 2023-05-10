package com.info.info_v2_backend.common.employment


data class EmploymentDto (
    val id: String,
    val student: EmploymentStudentResponse,
    val company: EmploymentCompanyResponse,
    val contactor: EmploymentContactorResponse,
    val status: EmploymentStatus
) {
    data class EmploymentStudentResponse(
        val studentEmail: String,
        val name: String,
        val generation: Int
    )
    data class EmploymentCompanyResponse(
        val companyNumber: String,
    )
    data class EmploymentContactorResponse(
        val contactorEmail: String
    )

}
