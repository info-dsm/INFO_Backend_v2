package com.info.info_v2_backend.notice.application.port.input

interface ApproveNoticeUsecase {

    fun approve(companyNumber: String, noticeId: String)
}