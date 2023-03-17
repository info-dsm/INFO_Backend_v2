package com.info.info_v2_backend.file.application.port.input.applies

interface RemoveResumeUsecase {

    fun remove(noticeId: String, studentEmail: String)
}