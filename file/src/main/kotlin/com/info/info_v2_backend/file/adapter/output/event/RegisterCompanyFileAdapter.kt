package com.info.info_v2_backend.file.adapter.output.event

import com.info.info_v2_backend.common.event.KafkaTopics
import com.info.info_v2_backend.common.file.dto.RegisterCompanyFileDto
import com.info.info_v2_backend.file.application.port.output.company.RegisterCompanyFilePort
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class RegisterCompanyFileAdapter(
    private val kafkaTemplate: KafkaTemplate<String, RegisterCompanyFileDto>
): RegisterCompanyFilePort {
    override fun register(fileDto: RegisterCompanyFileDto) {
        kafkaTemplate.send(KafkaTopics.REGISTER_COMPANY_FILE, fileDto)
    }

}