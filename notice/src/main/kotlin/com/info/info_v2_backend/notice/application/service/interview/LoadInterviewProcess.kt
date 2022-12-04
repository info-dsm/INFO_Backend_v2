package com.info.info_v2_backend.notice.application.service.interview

import com.info.info_v2_backend.notice.application.port.input.interview.LoadInterviewProcessUsecase
import com.info.info_v2_backend.notice.domain.interview.InterviewProcess
import org.springframework.stereotype.Service


@Service
class LoadInterviewProcess(): LoadInterviewProcessUsecase {
    override fun loadAll(): List<InterviewProcess> {
        return InterviewProcess.values().toList()
    }
}