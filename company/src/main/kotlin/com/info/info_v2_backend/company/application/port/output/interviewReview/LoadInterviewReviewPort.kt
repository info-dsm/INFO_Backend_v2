package com.info.info_v2_backend.company.application.port.output.interviewReview

import com.info.info_v2_backend.company.domain.interview.InterviewReview

interface LoadInterviewReviewPort {

    fun loadInterviewReviewById(id: Long): InterviewReview?
    fun loadInterviewReviewByCompanyNumber(companyNumber: String): List<InterviewReview>
}
