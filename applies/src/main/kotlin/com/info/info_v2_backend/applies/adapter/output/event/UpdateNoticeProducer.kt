package com.info.info_v2_backend.applies.adapter.output.event

import com.info.info_v2_backend.applies.application.port.output.save.UpdateNoticeAppliesCountPort
import com.info.info_v2_backend.common.event.KafkaTopics
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class UpdateNoticeProducer(
    private val kafkaTemplate: KafkaTemplate<String, String>
): UpdateNoticeAppliesCountPort {

    override fun addCount(noticeId: String) {
        kafkaTemplate.send(KafkaTopics.ADD_NOTICE_APPLIES_COUNT, noticeId)
    }

    override fun minusCount(noticeId: String) {
        kafkaTemplate.send(KafkaTopics.MINUS_NOTICE_APPLIES_COUNT, noticeId)
    }


}