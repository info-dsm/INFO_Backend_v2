package com.info.info_v2_backend.company.adapter.output.event

import com.info.info_v2_backend.common.event.KafkaTopics
import com.info.info_v2_backend.company.application.port.output.SaveContactorPort
import com.info.info_v2_backend.user.adapter.input.event.dto.ContactorDto
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class UserAdapter(
    private val contactorSender: KafkaTemplate<String, ContactorDto>
): SaveContactorPort {

    override fun save(contactorDto: ContactorDto) {
        contactorSender.send(KafkaTopics.SAVE_CONTACTOR, contactorDto)
    }
}