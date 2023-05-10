package com.info.info_v2_backend.company.adapter.input.event

import com.info.info_v2_backend.common.event.KafkaTopics
import com.info.info_v2_backend.company.application.port.input.notice.UpdateLastNoticedCompanyUsecase
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class UpdateCompanyLastNoticedConsumer(
    private val updateLastNoticedCompanyUsecase: UpdateLastNoticedCompanyUsecase
) {

    @KafkaListener(topics = [KafkaTopics.UPDATE_LAST_NOTICED_COMPANY], groupId = "info", containerFactory = "stringChangeListener")
    fun updateCompanyLastNoticedYear(companyNumber: String) {
        updateLastNoticedCompanyUsecase.update(companyNumber)
    }

}
