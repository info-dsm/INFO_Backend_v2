package com.info.info_v2_backend.notice.application.port.input.interview

import com.info.info_v2_backend.notice.domain.interview.InterviewProcess

interface LoadInterviewProcessUsecase {

    fun loadAll(): List<InterviewProcess>
}