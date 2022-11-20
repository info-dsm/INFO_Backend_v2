package com.info.info_v2_backend.notice.application.service

import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.common.exception.ErrorCode
import com.info.info_v2_backend.notice.application.port.input.ApproveNoticeUsecase
import com.info.info_v2_backend.notice.application.port.output.LoadNoticePort
import com.info.info_v2_backend.notice.application.port.output.SaveNoticePort
import org.springframework.stereotype.Service

@Service
class ApproveNotice(
    private val loadNoticePort: LoadNoticePort,
    private val saveNoticePort: SaveNoticePort
): ApproveNoticeUsecase {

    override fun approve(companyNumber: String, noticeId: String) {
        val notice = loadNoticePort.loadNotice(noticeId)
            ?: throw BusinessException(
                "모집공고를 조회하지 못했습니다.",
                ErrorCode.PERSISTENCE_DATA_NOT_FOUND_ERROR
            )

        notice.approve()

        saveNoticePort.saveNotice(notice)
    }


}