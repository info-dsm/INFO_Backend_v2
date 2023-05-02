package com.info.info_v2_backend.company.application.port.input.interviewReview

interface DeleteInterviewReviewUsecase {

    fun delete(id: Long, userEmail: String, companyNumber: String)
}
