package com.info.info_v2_backend.applies.application.service

import com.info.info_v2_backend.applies.application.port.input.RejectAppliesUsecase
import com.info.info_v2_backend.applies.application.port.output.applies.LoadAppliesPort
import com.info.info_v2_backend.applies.application.port.output.applies.SaveAppliesPort
import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.common.exception.ErrorCode
import org.springframework.stereotype.Service

@Service
class RejectApplies(
    private val loadAppliesPort: LoadAppliesPort,
    private val saveAppliesPort: SaveAppliesPort
): RejectAppliesUsecase {
    override fun reject(noticeId: String, studentEmail: String, message: String?) {
        val applies = loadAppliesPort.loadAppliesByNoticeAndStudentEmail(noticeId, studentEmail)
            ?: throw BusinessException("지원을 조회하지 못했습니다. -> $studentEmail", ErrorCode.PERSISTENCE_DATA_NOT_FOUND_ERROR)

        applies.reject(message)
        saveAppliesPort.save(applies)
    }
}