package com.info.info_v2_backend.company.application.port.input.interviewReview

import com.info.info_v2_backend.company.adapter.input.web.rest.dto.response.interviewReview.MaximumInterviewReviewResponse
import com.info.info_v2_backend.company.adapter.input.web.rest.dto.response.interviewReview.MinimumInterviewReviewResponse

interface LoadInterviewReviewUsecase {

    fun loadMinimumInterviewListByCompany(companyNumber: String): List<MinimumInterviewReviewResponse>
    fun loadMaximumInterviewById(id: Long): MaximumInterviewReviewResponse
}
