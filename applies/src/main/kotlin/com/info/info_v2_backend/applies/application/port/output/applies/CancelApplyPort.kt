package com.info.info_v2_backend.applies.application.port.output.applies

interface CancelApplyPort {

    fun cancelApply(noticeId: String, studentEmail: String)
}