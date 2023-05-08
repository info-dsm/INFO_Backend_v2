package com.info.info_v2_backend.company.application.service.interview

import com.info.info_v2_backend.company.application.port.input.interviewReview.DeleteInterviewReviewUsecase
import org.springframework.stereotype.Service

@Service
class DeleteInterviewReview: DeleteInterviewReviewUsecase {
    override fun delete(id: Long, userEmail: String, companyNumber: String) {
        TODO("Not yet implemented")
    }


}
