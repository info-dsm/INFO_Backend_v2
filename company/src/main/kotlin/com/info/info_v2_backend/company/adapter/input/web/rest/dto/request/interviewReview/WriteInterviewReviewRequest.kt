package com.info.info_v2_backend.company.adapter.input.web.rest.dto.request.interviewReview

import com.info.info_v2_backend.company.domain.interview.InterviewType
import java.time.LocalDate

data class WriteInterviewReviewRequest (
    val appliedJobPart: String,
    val interviewDate: LocalDate?,
    val directorCount: Int?,
    val interviewPlace: String?,
    val interviewType: InterviewType,
    val questionContent: String,
    val interviewImpression: String,
    val interviewCaution: String?
)
