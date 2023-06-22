package com.info.info_v2_backend.company.application.service.interview

import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.common.exception.ErrorCode
import com.info.info_v2_backend.company.adapter.input.web.rest.dto.request.interviewReview.EditInterviewReviewRequest
import com.info.info_v2_backend.company.application.port.input.interviewReview.EditInterviewReviewUsecase
import com.info.info_v2_backend.company.application.port.output.interviewReview.LoadInterviewReviewPort
import com.info.info_v2_backend.company.application.port.output.interviewReview.WriteInterviewReviewPort
import com.info.info_v2_backend.company.domain.interview.InterviewReview
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class EditInterviewReview(
    private val loadInterviewReviewPort: LoadInterviewReviewPort,
    private val saveInterviewReviewPort: WriteInterviewReviewPort
): EditInterviewReviewUsecase {

    override fun edit(id: Long, userEmail: String?, request: EditInterviewReviewRequest, companyNumber: String) {
        val interviewReview = (loadInterviewReviewPort.loadInterviewReviewById(id)
            ?: throw BusinessException(errorCode = ErrorCode.NO_DATA_FOUND_ERROR)).takeIf {
            review: InterviewReview ->
            return@takeIf userEmail?.let {
                email -> review.student.studentEmail == email
            }?: true
        }?: throw BusinessException(errorCode = ErrorCode.NO_AUTHORIZATION_ERROR)

        interviewReview.edit(request)
        saveInterviewReviewPort.save(interviewReview)
    }

}
