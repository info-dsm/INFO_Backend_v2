package com.info.info_v2_backend.notice.application.port.output

import com.info.info_v2_backend.notice.adapter.input.rest.dto.response.MinimumNoticeResponse
import com.info.info_v2_backend.notice.domain.status.NoticeWaitingStatus
import org.springframework.data.domain.Page
import java.time.LocalDate

interface LoadWithConditionPort {

    fun loadBeforeEndDateAndStatusNoticeList(idx: Int, size: Int, date: LocalDate, status: NoticeWaitingStatus): Page<MinimumNoticeResponse>
    fun loadAfterEndDateAndStatusNoticeList(idx: Int, size: Int, date: LocalDate, status: NoticeWaitingStatus): Page<MinimumNoticeResponse>
}