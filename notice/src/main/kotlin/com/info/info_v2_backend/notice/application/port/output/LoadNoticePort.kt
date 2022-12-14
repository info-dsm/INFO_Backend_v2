package com.info.info_v2_backend.notice.application.port.output

import com.info.info_v2_backend.notice.adapter.input.rest.dto.response.MaximumNoticeResponse
import com.info.info_v2_backend.notice.adapter.input.rest.dto.response.MinimumNoticeResponse
import com.info.info_v2_backend.notice.domain.Notice
import com.info.info_v2_backend.notice.domain.status.NoticeWaitingStatus
import org.springframework.data.domain.Page
import java.time.LocalDate

interface LoadNoticePort {

    fun loadNotice(noticeId: String): Notice?
    fun loadNoticeByCompany(companyNumber: String): List<Notice>

}