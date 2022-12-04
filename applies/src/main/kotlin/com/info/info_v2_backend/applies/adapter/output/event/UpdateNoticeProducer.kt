package com.info.info_v2_backend.applies.adapter.output.event

import com.info.info_v2_backend.applies.application.port.output.notice.UpdateNoticeAppliesCountPort
import com.info.info_v2_backend.common.event.KafkaTopics
import com.info.info_v2_backend.common.notice.UpdateNoticeAppliesCountDto
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class UpdateNoticeProducer(
    private val kafkaTemplate: KafkaTemplate<String, UpdateNoticeAppliesCountDto>
): UpdateNoticeAppliesCountPort {

    override fun addCount(noticeId: String) {
        kafkaTemplate.send(KafkaTopics.UPDATE_NOTICE_APPLIES_COUNT, UpdateNoticeAppliesCountDto(
            noticeId,
            1
        ))
    }

    override fun minusCount(noticeId: String) {
        kafkaTemplate.send(KafkaTopics.UPDATE_NOTICE_APPLIES_COUNT, UpdateNoticeAppliesCountDto(
            noticeId,
            -1
        ))
    }


}