package com.info.info_v2_backend.applies.application.service

import com.info.info_v2_backend.applies.application.port.input.ApplyAppliesUsecase
import com.info.info_v2_backend.applies.application.port.output.applies.CancelApplyPort
import com.info.info_v2_backend.applies.application.port.output.applies.LoadAppliesPort
import com.info.info_v2_backend.applies.application.port.output.applies.SaveAppliesPort
import com.info.info_v2_backend.applies.application.port.output.company.LoadCompanyPort
import com.info.info_v2_backend.applies.application.port.output.notice.LoadNoticePort
import com.info.info_v2_backend.applies.application.port.output.notice.UpdateNoticeAppliesCountPort
import com.info.info_v2_backend.applies.application.port.output.student.LoadStudentPort
import com.info.info_v2_backend.applies.application.port.output.resume.ResumePort
import com.info.info_v2_backend.applies.domain.Applies
import com.info.info_v2_backend.applies.domain.company.AppliesCompany
import com.info.info_v2_backend.applies.domain.notice.AppliesNotice
import com.info.info_v2_backend.applies.domain.user.Applicant
import com.info.info_v2_backend.common.auth.Auth
import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.common.exception.ErrorCode
import com.info.info_v2_backend.common.file.dto.request.GenerateFileListRequest
import com.info.info_v2_backend.common.file.dto.response.PresignedUrlListResponse
import com.info.info_v2_backend.common.file.dto.response.PresignedUrlResponse
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ApplyApplies(
    private val loadNoticePort: LoadNoticePort,
    private val loadStudentPort: LoadStudentPort,
    private val applyAppliesPort: SaveAppliesPort,
    private val resumePort: ResumePort,
    private val updateNoticeAppliesCountPort: UpdateNoticeAppliesCountPort,
    private val cancelApply: CancelApplyPort,
    private val loadCompanyPort: LoadCompanyPort,
    private val loadAppliesPort: LoadAppliesPort
): ApplyAppliesUsecase {

    private val log = LoggerFactory.getLogger(this.javaClass)

    @Transactional
    override fun apply(noticeId: String, request: GenerateFileListRequest, studentEmail: String): PresignedUrlListResponse {
        val student = loadStudentPort.loadStudent(
            studentEmail
        )?: throw BusinessException(
            "사용자를 찾지 못했습니다. -> ${Auth.getUserEmail()}",
            ErrorCode.PERSISTENCE_DATA_NOT_FOUND_ERROR
        )

        val notice = loadNoticePort.loadAvailableNotice(
            noticeId
        )?: throw BusinessException("채용공고를 조회히자 못했습니다. -> $noticeId", ErrorCode.PERSISTENCE_DATA_NOT_FOUND_ERROR)

        val company = loadCompanyPort.loadCompany(notice.companyNumber)
            ?: throw BusinessException(errorCode = ErrorCode.NO_DATA_FOUND_ERROR)

        loadAppliesPort.loadAppliesByNoticeAndStudentEmail(noticeId, studentEmail)?.let {
            cancelApply.cancelApply(noticeId, studentEmail)
        }?:let {
            updateNoticeAppliesCountPort.addCount(noticeId)
        }
        val applies = applyAppliesPort.save(
            Applies(
                Applicant(
                    student.email,
                    student.name,
                    student.entranceYear
                ),
                AppliesNotice(
                    notice.noticeId,
                    notice.classificationList
                ),
                AppliesCompany(
                    company.companyNumber,
                    company.companyName
                ),
                null
            )
        )
        
        resumePort.removeResume(noticeId, studentEmail)
        return resumePort.uploadResume(noticeId, applies.applicant.email, request)
    }


}