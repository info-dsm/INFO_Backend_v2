package com.info.info_v2_backend.employment.adapter.input.rest.dto.response

data class AnonymousEmploymentListResponse(
    val classNum: Int,
    val totalClassStudent: Int,
    val totalEmployedClassStudent: Int,
    val totalGradeStudent: Int,
    val totalEmployedGradeStudent: Int,
    val employmentList: List<AnonymousEmploymentResponse>
) {
    data class AnonymousEmploymentResponse(
        val comapny: AnonymousEmploymentCompanyResponse,
        val classNum: Int
    ) {
        data class AnonymousEmploymentCompanyResponse(
            val companyName: String,
            val companyLogo: String
        )
    }

}
