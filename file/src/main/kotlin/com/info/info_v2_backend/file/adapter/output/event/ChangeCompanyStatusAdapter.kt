package com.info.info_v2_backend.file.adapter.output.event

import com.info.info_v2_backend.common.event.KafkaTopics
import com.info.info_v2_backend.file.application.port.output.company.ChangeCompanyStatusPort
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class ChangeCompanyStatusAdapter(
    private val kafkaTemplate: KafkaTemplate<String, String>
): ChangeCompanyStatusPort {
    override fun change(companyNumber: String, sequence: Int) {

        kafkaTemplate.send(KafkaTopics.CHANGE_COMPANY_STATUS, "$companyNumber$sequence")
    }
}