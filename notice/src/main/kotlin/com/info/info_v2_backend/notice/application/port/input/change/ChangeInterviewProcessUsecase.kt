package com.info.info_v2_backend.notice.application.port.input.change

import com.info.info_v2_backend.notice.domain.interview.InterviewProcess

interface ChangeInterviewProcessUsecase {

    fun change(noticeId: String, interviewProcessMap: Map<Int, InterviewProcess>)
}