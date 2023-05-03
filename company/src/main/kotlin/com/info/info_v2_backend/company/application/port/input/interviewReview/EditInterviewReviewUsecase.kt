package com.info.info_v2_backend.company.application.port.input.interviewReview

import com.info.info_v2_backend.company.adapter.input.web.rest.dto.request.interviewReview.EditInterviewReviewRequest

interface EditInterviewReviewUsecase {

    fun edit(id: Long, userEmail: String, request: EditInterviewReviewRequest, companyNumber: String)
}
