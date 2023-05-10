package com.info.info_v2_backend.company.adapter.output.persistence.rdb

import com.info.info_v2_backend.company.adapter.output.persistence.rdb.repository.InterviewReviewRepository
import com.info.info_v2_backend.company.application.port.output.interviewReview.DeleteInterviewReviewPort
import com.info.info_v2_backend.company.application.port.output.interviewReview.LoadInterviewReviewPort
import com.info.info_v2_backend.company.application.port.output.interviewReview.WriteInterviewReviewPort
import com.info.info_v2_backend.company.domain.interview.InterviewReview
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class InterviewReviewAdapter(
    private val interviewReviewRepository: InterviewReviewRepository
): DeleteInterviewReviewPort, LoadInterviewReviewPort, WriteInterviewReviewPort {

    override fun delete(id: Long) {
        interviewReviewRepository.deleteById(id)
    }

    override fun loadInterviewReviewById(id: Long): InterviewReview? {
        return interviewReviewRepository.findById(id).orElse(null)
    }

    override fun loadInterviewReviewByCompanyNumber(companyNumber: String): List<InterviewReview> {
        return interviewReviewRepository.findByCompanyNumber(companyNumber)
    }

    override fun save(review: InterviewReview) {
        interviewReviewRepository.save(review)
    }
}
