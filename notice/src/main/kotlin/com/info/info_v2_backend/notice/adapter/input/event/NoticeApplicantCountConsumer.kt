package com.info.info_v2_backend.notice.adapter.input.event

import com.info.info_v2_backend.common.event.KafkaTopics
import com.info.info_v2_backend.common.notice.UpdateNoticeAppliesCountDto
import com.info.info_v2_backend.notice.application.port.input.UpdateNoticeApplicantCountUsecase
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class NoticeApplicantCountConsumer(
    private val updateNoticeApplicantCountUsecase: UpdateNoticeApplicantCountUsecase
) {

    @KafkaListener(topics = [KafkaTopics.UPDATE_NOTICE_APPLIES_COUNT], groupId = "info", containerFactory = "updateNoticeApplicantCountChangeListener")
    fun updateNoticeApplicantCount(dto: UpdateNoticeAppliesCountDto ) {
        updateNoticeApplicantCountUsecase.update(dto.noticeId, dto.count)
    }

}