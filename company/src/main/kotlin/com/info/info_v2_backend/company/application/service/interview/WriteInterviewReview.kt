package com.info.info_v2_backend.company.application.service.interview

import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.common.exception.ErrorCode
import com.info.info_v2_backend.company.adapter.input.web.rest.dto.request.interviewReview.WriteInterviewReviewRequest
import com.info.info_v2_backend.company.application.port.input.interviewReview.WriteInterviewReviewUsecase
import com.info.info_v2_backend.company.application.port.output.company.LoadCompanyPort
import com.info.info_v2_backend.company.application.port.output.interviewReview.WriteInterviewReviewPort
import com.info.info_v2_backend.company.application.port.output.user.LoadUserPort
import com.info.info_v2_backend.company.domain.interview.InterviewReview
import com.info.info_v2_backend.company.domain.interview.InterviewReviewStudent
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class WriteInterviewReview(
    private val writeInterviewReviewPort: WriteInterviewReviewPort,
    private val loadStudentPort: LoadUserPort,
    private val loadCompanyPort: LoadCompanyPort
): WriteInterviewReviewUsecase {

    override fun write(request: WriteInterviewReviewRequest, userEmail: String, companyNumber: String) {
        val student = loadStudentPort.load(userEmail)
            ?: throw BusinessException(errorCode = ErrorCode.NO_DATA_FOUND_ERROR)

        val company = loadCompanyPort.loadCompany(companyNumber)
            ?: throw BusinessException(errorCode = ErrorCode.NO_DATA_FOUND_ERROR)

        writeInterviewReviewPort.save(
            InterviewReview(
                InterviewReviewStudent(
                    student.email,
                    student.studentKey,
                    student.name
                ),
                company,
                request.appliedJobPart,
                request.interviewDate,
                request.directorCount,
                request.interviewPlace,
                request.interviewType,
                request.questionContent,
                request.interviewImpression,
                request.interviewCaution
            )
        )
    }

}
