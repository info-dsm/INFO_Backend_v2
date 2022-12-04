package com.info.info_v2_backend.notice.application.service

import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.common.exception.ErrorCode
import com.info.info_v2_backend.notice.application.port.input.UpdateNoticeApplicantCountUsecase
import com.info.info_v2_backend.notice.application.port.output.LoadNoticePort
import com.info.info_v2_backend.notice.application.port.output.SaveNoticePort
import org.springframework.stereotype.Service


@Service
class UpdateNoticeApplicantCount(
    private val loadNoticePort: LoadNoticePort,
    private val saveNoticePort: SaveNoticePort
): UpdateNoticeApplicantCountUsecase {
    override fun update(noticeId: String, count: Int) {
        val notice = loadNoticePort.loadNotice(noticeId)
            ?: throw BusinessException(
                "모집공고를 조회할 수 없습니다.",
                ErrorCode.PERSISTENCE_DATA_NOT_FOUND_ERROR
            )
        notice.updateCount(count)

        saveNoticePort.saveNotice(notice)
    }


}