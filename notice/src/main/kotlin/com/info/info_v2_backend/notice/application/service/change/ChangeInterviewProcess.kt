package com.info.info_v2_backend.notice.application.service.change

import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.common.exception.ErrorCode
import com.info.info_v2_backend.notice.application.port.input.change.ChangeInterviewProcessUsecase
import com.info.info_v2_backend.notice.application.port.output.LoadNoticePort
import com.info.info_v2_backend.notice.application.port.output.SaveNoticePort
import com.info.info_v2_backend.notice.domain.interview.InterviewProcess
import org.springframework.stereotype.Service


@Service
class ChangeInterviewProcess(
    private val loadNoticePort: LoadNoticePort,
    private val saveNoticePort: SaveNoticePort
): ChangeInterviewProcessUsecase {
    override fun change(noticeId: String, interviewProcessMap: Map<Int, InterviewProcess>) {
        val notice = loadNoticePort.loadNotice(noticeId)
            ?: throw BusinessException(
                "모집공고를 조회하지 못했습니다.",
            ErrorCode.PERSISTENCE_DATA_NOT_FOUND_ERROR
            )

        notice.changeInterviewProcess(interviewProcessMap.toMutableMap())
        saveNoticePort.saveNotice(notice)
    }


}