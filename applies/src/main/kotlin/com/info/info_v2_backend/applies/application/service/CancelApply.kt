package com.info.info_v2_backend.applies.application.service

import com.info.info_v2_backend.applies.application.port.input.CancelApplyUsecase
import com.info.info_v2_backend.applies.application.port.output.applies.CancelApplyPort
import com.info.info_v2_backend.applies.application.port.output.applies.LoadAppliesPort
import com.info.info_v2_backend.applies.application.port.output.notice.UpdateNoticeAppliesCountPort
import com.info.info_v2_backend.applies.application.port.output.resume.ResumePort
import com.info.info_v2_backend.common.applies.AppliesStatus
import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.common.exception.ErrorCode
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CancelApply(
    private val loadAppliesPort: LoadAppliesPort,
    private val cancelApplyPort: CancelApplyPort,
    private val updateNoticeAppliesCountPort: UpdateNoticeAppliesCountPort,
    private val resumePort: ResumePort
): CancelApplyUsecase {

    @Transactional
    override fun cancelApply(noticeId: String, studentEmail: String) {
        val applies = loadAppliesPort.loadAppliesByNoticeAndStudentEmail(noticeId, studentEmail)
            ?: throw BusinessException(
                "모집공고를 조회할 수 없습니다. -> notice: $noticeId, email: $studentEmail",
                ErrorCode.PERSISTENCE_DATA_NOT_FOUND_ERROR)
        if (applies.status == AppliesStatus.APPROVE)
            updateNoticeAppliesCountPort.minusCount(noticeId)
        cancelApplyPort.cancelApply(noticeId, studentEmail)
        resumePort.removeResume(noticeId, studentEmail)
    }

}