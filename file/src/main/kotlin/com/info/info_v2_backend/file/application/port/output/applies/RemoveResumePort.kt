package com.info.info_v2_backend.file.application.port.output.applies

import com.info.info_v2_backend.file.domain.applies.ResumeNotice
import com.info.info_v2_backend.file.domain.applies.ResumeStudent

interface RemoveResumePort {

    fun remove(noticeId: String, studentEmail: String)
}