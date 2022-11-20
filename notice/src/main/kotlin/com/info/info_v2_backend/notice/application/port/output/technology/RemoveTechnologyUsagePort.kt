package com.info.info_v2_backend.notice.application.port.output.technology

interface RemoveTechnologyUsagePort {

    fun removeByNoticeId(noticeId: String)
}