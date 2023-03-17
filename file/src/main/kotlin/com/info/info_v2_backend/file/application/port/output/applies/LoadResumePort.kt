package com.info.info_v2_backend.file.application.port.output.applies

import com.info.info_v2_backend.file.domain.applies.Resume

interface LoadResumePort {

    fun load(noticeId: String, studentEmail: String): List<Resume>
}