package com.info.info_v2_backend.applies.application.port.output.load

import com.info.info_v2_backend.common.notice.NoticeDto

interface LoadNoticePort {

    fun loadAvailableNotice(noticeId: String): NoticeDto?
}