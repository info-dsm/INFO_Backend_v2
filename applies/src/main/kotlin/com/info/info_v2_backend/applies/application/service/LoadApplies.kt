package com.info.info_v2_backend.applies.application.service

import com.info.info_v2_backend.applies.adapter.input.rest.dto.respnose.AppliesResponse
import com.info.info_v2_backend.applies.application.port.input.LoadAppliesUsecase
import com.info.info_v2_backend.applies.application.port.output.applies.LoadAppliesPort
import com.info.info_v2_backend.applies.application.port.output.resume.ResumePort
import com.info.info_v2_backend.common.applies.AppliesDto
import com.info.info_v2_backend.common.applies.AppliesStatus
import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.common.exception.ErrorCode
import org.springframework.stereotype.Service

@Service
class LoadApplies(
    private val loadAppliesPort: LoadAppliesPort,
    private val resumePort: ResumePort
): LoadAppliesUsecase {

    override fun loadAppliesListByStatus(companyNumber: String, noticeId: String, status: AppliesStatus?): List<AppliesResponse> {
        return loadAppliesPort.loadAppliesList(noticeId, status).map {
            if (!(it.notice.companyNumber == companyNumber)) throw BusinessException(null, ErrorCode.INVALID_INPUT_DATA_ERROR)
            it.toAppliesResponse(
                resumePort.loadAppliesResume(it.notice.noticeId, it.applicant.email)
            )
        }
    }

    override fun loadApplies(noticeId: String, studentEmail: String): AppliesDto? {
        return loadAppliesPort.loadAppliesByNoticeAndStudentEmail(noticeId, studentEmail)
            ?.toAppliesDto()
    }

    override fun loadEveryAppliesListByStatus(status: AppliesStatus): List<AppliesResponse> {
        return loadAppliesPort.loadEveryAppliesByStatus(status).map {
            it.toAppliesResponse(
                resumePort.loadAppliesResume(it.notice.noticeId, it.applicant.email)
            )
        }
    }

    override fun loadAppliesListByStudentEmail(studentEmail: String): List<AppliesResponse> {
        return loadAppliesPort.loadAppliesByStudentEmail(studentEmail).map {
            it.toAppliesResponse(
                resumePort.loadAppliesResume(it.notice.noticeId, it.applicant.email)
            )
        }
    }


}