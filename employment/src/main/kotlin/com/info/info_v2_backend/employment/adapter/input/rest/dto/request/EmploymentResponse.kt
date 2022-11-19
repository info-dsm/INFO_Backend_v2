package com.info.info_v2_backend.employment.adapter.input.rest.dto.request

import com.info.info_v2_backend.employment.domain.company.EmploymentContactor

data class EmploymentResponse(
    val id: String,
    val notice: EmploymentNoticeResponse,
    val student: EmploymentStudentResponse,
    val company: EmploymentCompanyResponse,
    val contactor: EmploymentContactorResponse

) {
    data class EmploymentNoticeResponse(
        val noticeId: String
    )
    data class EmploymentStudentResponse(
        val studentEmail: String,
        val name: String,
        val generation: Int
    )
    data class EmploymentCompanyResponse(
        val companyNumber: String,
        val companyName: String
    )
    data class EmploymentContactorResponse(
        val contactorEmail: String
    )

}
