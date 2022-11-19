package com.info.info_v2_backend.notice.application.service

import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.common.exception.ErrorCode
import com.info.info_v2_backend.notice.application.port.input.ConcludeNoticeUsecase
import com.info.info_v2_backend.notice.application.port.output.LoadNoticePort
import com.info.info_v2_backend.notice.application.port.output.SaveNoticePort
import org.springframework.stereotype.Service

@Service
class ConcludeNotice(
    private val loadNoticePort: LoadNoticePort,
    private val saveNoticePort: SaveNoticePort
): ConcludeNoticeUsecase {

    override fun conclude(noticeId: String) {
        val notice = loadNoticePort.loadNotice(noticeId)
            ?: throw BusinessException(
                "모집공고를 조회하지 못했습니다. -> $noticeId",
                ErrorCode.PERSISTENCE_DATA_NOT_FOUND_ERROR
            )
        notice.conclude()
        saveNoticePort.saveNotice(notice)
    }


}