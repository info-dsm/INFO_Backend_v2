package com.info.info_v2_backend.notice.adapter.output.event

import com.info.info_v2_backend.common.event.KafkaTopics
import com.info.info_v2_backend.notice.application.port.output.UpdateCompanyPort
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class CompanyAdapter(
    private val kafkaTemplate: KafkaTemplate<String, String>
): UpdateCompanyPort {
    override fun updateLastNoticedYear(companyNumber: String) {
        kafkaTemplate.send(KafkaTopics.UPDATE_LAST_NOTICED_COMPANY, companyNumber)
    }


}