package com.info.info_v2_backend.company.adapter.output.event

import com.info.info_v2_backend.common.event.KafkaTopics
import com.info.info_v2_backend.company.application.port.output.company.SaveContactorPort
import com.info.info_v2_backend.user.adapter.input.web.rest.dto.request.SaveContactorDto
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class UserAdapter(
    private val contactorSender: KafkaTemplate<String, SaveContactorDto>
): SaveContactorPort {

    override fun save(saveContactorDto: SaveContactorDto) {
        contactorSender.send(KafkaTopics.SAVE_CONTACTOR, saveContactorDto)
    }
}