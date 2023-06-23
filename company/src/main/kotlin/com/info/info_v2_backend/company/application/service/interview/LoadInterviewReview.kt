package com.info.info_v2_backend.company.application.service.interview

import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.common.exception.ErrorCode
import com.info.info_v2_backend.company.adapter.input.web.rest.dto.response.interviewReview.MaximumInterviewReviewResponse
import com.info.info_v2_backend.company.adapter.input.web.rest.dto.response.interviewReview.MinimumInterviewReviewResponse
import com.info.info_v2_backend.company.application.port.input.interviewReview.LoadInterviewReviewUsecase
import com.info.info_v2_backend.company.application.port.output.interviewReview.LoadInterviewReviewPort
import org.springframework.stereotype.Service

@Service
class LoadInterviewReview(
    private val loadInterviewReviewPort: LoadInterviewReviewPort
): LoadInterviewReviewUsecase {

    override fun loadMinimumInterviewListByCompany(companyNumber: String): List<MinimumInterviewReviewResponse> {
        return loadInterviewReviewPort.loadInterviewReviewByCompanyNumber(companyNumber).map {
            it.toMinimumInterviewReview()
        }
    }

    override fun loadMaximumInterviewById(id: Long): MaximumInterviewReviewResponse {
        return loadInterviewReviewPort.loadInterviewReviewById(id)?.toMaximumInterviewReview() ?: throw BusinessException(errorCode = ErrorCode.NO_DATA_FOUND_ERROR)
    }


}
