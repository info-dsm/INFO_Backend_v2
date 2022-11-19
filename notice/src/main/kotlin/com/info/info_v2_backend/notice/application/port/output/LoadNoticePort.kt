package com.info.info_v2_backend.notice.application.port.output

import com.info.info_v2_backend.notice.domain.Notice
import org.springframework.data.domain.Page
import java.time.LocalDate

interface LoadNoticePort {

    fun loadNotice(noticeId: String): Notice?
    fun loadOnDateAndApproveNoticeList(idx: Int, size: Int, date: LocalDate): Page<Notice>
}