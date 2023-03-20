package com.info.info_v2_backend.file.application.service

import com.info.info_v2_backend.file.application.port.input.applies.RemoveResumeUsecase
import com.info.info_v2_backend.file.application.port.output.applies.RemoveResumePort
import org.springframework.stereotype.Service

@Service
class RemoveResume(
    private val removeResumePort: RemoveResumePort
): RemoveResumeUsecase {

    override fun remove(noticeId: String, studentEmail: String) {
        removeResumePort.remove(noticeId, studentEmail)
    }


}