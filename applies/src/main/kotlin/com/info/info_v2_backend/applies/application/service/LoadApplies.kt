package com.info.info_v2_backend.applies.application.service

import com.info.info_v2_backend.applies.adapter.input.rest.dto.respnose.AppliesResponse
import com.info.info_v2_backend.applies.application.port.input.LoadAppliesUsecase
import com.info.info_v2_backend.applies.application.port.output.applies.LoadAppliesPort
import com.info.info_v2_backend.applies.application.port.output.resume.ResumePort
import com.info.info_v2_backend.applies.domain.Applies
import com.info.info_v2_backend.common.applies.AppliesDto
import com.info.info_v2_backend.common.applies.AppliesStatus
import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.common.exception.ErrorCode
import com.info.info_v2_backend.common.file.dto.response.FileResponse
import org.springframework.stereotype.Service

@Service
class LoadApplies(
    private val loadAppliesPort: LoadAppliesPort,
    private val resumePort: ResumePort
): LoadAppliesUsecase {

    override fun loadAppliesListByStatus(companyNumber: String, noticeId: String, status: AppliesStatus?): List<AppliesResponse> {
        return loadAppliesPort.loadAppliesList(noticeId, status).map {
            applies: Applies ->
            if (!(applies.notice.companyNumber == companyNumber)) throw BusinessException(null, ErrorCode.INVALID_INPUT_DATA_ERROR)
            resumePort.loadAppliesResume(applies.notice.noticeId, applies.applicant.email)?.let {
                resume: FileResponse ->
                return@let applies.toAppliesResponse(resume)
            }?: throw BusinessException(null, ErrorCode.PERSISTENCE_DATA_NOT_FOUND_ERROR)
        }
    }

    override fun loadApplies(noticeId: String, studentEmail: String): AppliesDto? {
        return loadAppliesPort.loadAppliesByNoticeAndStudentEmail(noticeId, studentEmail)
            ?.toAppliesDto()
    }

    override fun loadEveryAppliesListByStatus(status: AppliesStatus): List<AppliesResponse> {
        return loadAppliesPort.loadEveryAppliesByStatus(status).map {
            applies: Applies ->
            resumePort.loadAppliesResume(applies.notice.noticeId, applies.applicant.email)?.let {
                    resume: FileResponse ->
                return@let applies.toAppliesResponse(resume)
            }?: throw BusinessException(null, ErrorCode.PERSISTENCE_DATA_NOT_FOUND_ERROR)
        }
    }

    override fun loadAppliesListByStudentEmail(studentEmail: String): List<AppliesResponse> {
        return loadAppliesPort.loadAppliesByStudentEmail(studentEmail).map {
                applies: Applies ->
            resumePort.loadAppliesResume(applies.notice.noticeId, applies.applicant.email)?.let {
                    resume: FileResponse ->
                return@let applies.toAppliesResponse(resume)
            }?: throw BusinessException(null, ErrorCode.PERSISTENCE_DATA_NOT_FOUND_ERROR)
        }
    }


}