package com.info.info_v2_backend.applies.application.port.input

interface CancelApplyUsecase {


    fun cancelApply(noticeId: String, studentEmail: String)
}