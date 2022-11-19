package com.info.info_v2_backend.applies.application.port.input

interface ApproveAppliesUsecase {

    fun approve(noticeId: String, studentEmail: String)
}