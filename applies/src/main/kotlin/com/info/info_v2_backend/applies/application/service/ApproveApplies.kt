package com.info.info_v2_backend.applies.application.service

import com.info.info_v2_backend.applies.application.port.input.ApproveAppliesUsecase
import com.info.info_v2_backend.applies.application.port.output.applies.LoadAppliesPort
import com.info.info_v2_backend.applies.application.port.output.applies.SaveAppliesPort
import com.info.info_v2_backend.applies.application.port.output.notice.UpdateNoticeAppliesCountPort
import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.common.exception.ErrorCode
import org.springframework.stereotype.Service

@Service
class ApproveApplies(
    private val loadAppliesPort: LoadAppliesPort,
    private val saveAppliesPort: SaveAppliesPort
): ApproveAppliesUsecase {

    override fun approve(noticeId: String, studentEmail: String) {
        val applies = loadAppliesPort.loadAppliesByNoticeAndStudentEmail(noticeId, studentEmail)
            ?: throw BusinessException("지원을 조회하지 못했습니다. -> $studentEmail", ErrorCode.PERSISTENCE_DATA_NOT_FOUND_ERROR)

        applies.approve()
        saveAppliesPort.save(applies)
    }
}