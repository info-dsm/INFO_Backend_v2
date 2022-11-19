package com.info.info_v2_backend.employment.application.port.output

import com.info.info_v2_backend.common.notice.NoticeDto

interface LoadNoticePort {

    fun loadNotice(noticeId: String): NoticeDto?
}