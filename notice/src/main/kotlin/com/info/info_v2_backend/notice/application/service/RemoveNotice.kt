package com.info.info_v2_backend.notice.application.service

import com.info.info_v2_backend.notice.application.port.input.RemoveNoticeUsecase
import com.info.info_v2_backend.notice.application.port.output.RemoveNoticePort
import org.springframework.stereotype.Service

@Service
class RemoveNotice(
    private val removeNoticePort: RemoveNoticePort
): RemoveNoticeUsecase {

    override fun remove(noticeId: String, companyNumber: String) {
        removeNoticePort.remove(noticeId)
    }


}