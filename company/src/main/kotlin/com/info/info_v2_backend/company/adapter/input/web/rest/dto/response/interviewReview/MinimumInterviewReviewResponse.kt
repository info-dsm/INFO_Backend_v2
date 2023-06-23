package com.info.info_v2_backend.company.adapter.input.web.rest.dto.response.interviewReview

import com.info.info_v2_backend.company.domain.interview.InterviewReviewStudent
import java.time.LocalDate

data class MinimumInterviewReviewResponse(
    val id: Long,
    val companyNumber: String,
    val appliedJobPart: String,
    val student: InterviewReviewStudent,
    val interviewDate: LocalDate?,
    val writtenDate: LocalDate
)
