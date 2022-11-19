package com.info.info_v2_backend.notice.application.port.input

interface RemoveNoticeUsecase {

    fun remove(noticeId: String, companyNumber: String)
}