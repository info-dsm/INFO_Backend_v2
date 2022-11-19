package com.info.info_v2_backend.applies.application.port.output.save

interface UpdateNoticeAppliesCountPort {

    fun addCount(noticeId: String)
    fun minusCount(noticeId: String)
}