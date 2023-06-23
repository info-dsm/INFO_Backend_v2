package com.info.info_v2_backend.company.application.service.interview

import com.info.info_v2_backend.company.adapter.output.persistence.rdb.repository.InterviewReviewRepository
import com.info.info_v2_backend.company.application.port.input.interviewReview.DeleteInterviewReviewUsecase
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class DeleteInterviewReview(
    private val interviewReviewRepository: InterviewReviewRepository
): DeleteInterviewReviewUsecase {

    override fun delete(id: Long, userEmail: String?, companyNumber: String) {
        userEmail?.let {
            interviewReviewRepository.deleteByIdAndStudentAndCompany(id, it, companyNumber)
        }?: interviewReviewRepository.deleteByIdAndCompany(id, companyNumber)
    }


}
