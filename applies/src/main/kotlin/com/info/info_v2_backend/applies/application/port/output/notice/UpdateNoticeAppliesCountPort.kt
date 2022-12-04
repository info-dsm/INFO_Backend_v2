package com.info.info_v2_backend.applies.application.port.output.notice

interface UpdateNoticeAppliesCountPort {

    fun addCount(noticeId: String)
    fun minusCount(noticeId: String)
}