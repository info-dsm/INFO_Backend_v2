package com.info.info_v2_backend.company.application.port.input.interviewReview

import com.info.info_v2_backend.company.adapter.input.web.rest.dto.request.interviewReview.WriteInterviewReviewRequest

interface WriteInterviewReviewUsecase {

    fun write(request: WriteInterviewReviewRequest, userEmail: String, companyNumber: String)
}
