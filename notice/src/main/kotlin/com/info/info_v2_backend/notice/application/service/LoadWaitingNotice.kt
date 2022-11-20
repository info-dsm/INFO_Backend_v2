package com.info.info_v2_backend.notice.application.service

import com.info.info_v2_backend.notice.adapter.input.rest.dto.response.MinimumNoticeResponse
import com.info.info_v2_backend.notice.application.port.input.LoadWaitingNoticeUsecase
import com.info.info_v2_backend.notice.application.port.output.LoadNoticePort
import com.info.info_v2_backend.notice.domain.status.NoticeWaitingStatus
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class LoadWaitingNotice(
    private val loadNoticePort: LoadNoticePort
): LoadWaitingNoticeUsecase {

    override fun load(idx: Int, size: Int): Page<MinimumNoticeResponse> {
        return loadNoticePort.loadOnDateAndStatusNoticeList(
            idx,
            size,
            LocalDate.now(),
            NoticeWaitingStatus.WAITING
        ).map {
            it.toMinimumNoticeResponse()
        }
    }


}