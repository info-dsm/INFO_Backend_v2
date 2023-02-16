package com.info.info_v2_backend.file.application.port.output.applies

interface RemoveResumePort {

    fun remove(noticeId: String, studentEmail: String)
}