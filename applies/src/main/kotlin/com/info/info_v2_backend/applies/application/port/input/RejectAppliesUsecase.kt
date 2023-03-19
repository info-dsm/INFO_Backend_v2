package com.info.info_v2_backend.applies.application.port.input

interface RejectAppliesUsecase {

    fun reject(noticeId: String, studentEmail: String, message: String?)
}