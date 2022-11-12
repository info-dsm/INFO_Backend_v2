package com.info.info_v2_backend.email.adapter.output.event

import com.info.info_v2_backend.email.adapter.input.event.configuration.KafkaProperty
import org.slf4j.LoggerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class UserEventAdapter(
    private val kafkaTemplate: KafkaTemplate<String, String>,
    private val kafkaProperty: KafkaProperty
) {

    private val log = LoggerFactory.getLogger(this.javaClass)


}