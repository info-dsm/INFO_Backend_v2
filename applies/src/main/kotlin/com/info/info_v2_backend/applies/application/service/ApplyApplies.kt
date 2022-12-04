package com.info.info_v2_backend.applies.application.service

import com.info.info_v2_backend.applies.application.port.input.ApplyAppliesUsecase
import com.info.info_v2_backend.applies.application.port.output.applies.SaveAppliesPort
import com.info.info_v2_backend.applies.application.port.output.notice.LoadNoticePort
import com.info.info_v2_backend.applies.application.port.output.student.LoadStudentPort
import com.info.info_v2_backend.applies.application.port.output.resume.ResumePort
import com.info.info_v2_backend.applies.domain.Applies
import com.info.info_v2_backend.applies.domain.notice.AppliesNotice
import com.info.info_v2_backend.applies.domain.user.Applicant
import com.info.info_v2_backend.common.auth.Auth
import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.common.exception.ErrorCode
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class ApplyApplies(
    private val loadNoticePort: LoadNoticePort,
    private val loadStudentPort: LoadStudentPort,
    private val applyAppliesPort: SaveAppliesPort,
    private val resumePort: ResumePort
): ApplyAppliesUsecase {
    override fun apply(noticeId: String, resume: MultipartFile, studentEmail: String) {
        val student = loadStudentPort.loadStudent(
            studentEmail
        )?: throw BusinessException(
            "사용자를 찾지 못했습니다. -> ${Auth.getUserEmail()}",
            ErrorCode.PERSISTENCE_DATA_NOT_FOUND_ERROR
        )
        val notice = loadNoticePort.loadAvailableNotice(
            noticeId
        )?: throw BusinessException("채용공고를 조회히자 못했습니다. -> $noticeId", ErrorCode.PERSISTENCE_DATA_NOT_FOUND_ERROR)

        val applies = applyAppliesPort.save(
            Applies(
                Applicant(
                    student.email,
                    student.name,
                    student.entranceYear
                ),
                AppliesNotice(
                    notice.noticeId,
                    notice.companyNumber
                )
            )
        )
        resumePort.uploadResume(noticeId, applies.id, resume)
    }


}