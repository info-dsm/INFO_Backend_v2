package com.info.info_v2_backend.employment.adapter.input.rest.dto.response

import com.info.info_v2_backend.employment.domain.generation.GenerationClass

data class AnonymousEmploymentListResponse(
    val classNum: Int,
    val information: GenerationClass,
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
            val companyNumber: String,
            val companyName: String,
            val companyLogo: String
        )
    }

}
