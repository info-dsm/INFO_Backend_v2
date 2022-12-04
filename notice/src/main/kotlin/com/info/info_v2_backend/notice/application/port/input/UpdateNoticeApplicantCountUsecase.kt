package com.info.info_v2_backend.notice.application.port.input

interface UpdateNoticeApplicantCountUsecase {

    fun update(noticeId: String, count: Int)
}