package com.info.info_v2_backend.company.adapter.input.web.rest.dto.response.interviewReview

import com.info.info_v2_backend.company.domain.interview.InterviewReviewStudent
import java.time.LocalDate

data class MaximumInterviewReviewResponse(
    val id: Long,
    val student: InterviewReviewStudent,
    val companyNumber: String,
    val appliedJobPart: String,
    val interviewDate: LocalDate?,
    val directorCount: Int?,
    val interviewPlace: String?,
    val questionContent: String,
    val interviewImpression: String,
    val interviewCaution: String?,
    val writtenDate: LocalDate

)
