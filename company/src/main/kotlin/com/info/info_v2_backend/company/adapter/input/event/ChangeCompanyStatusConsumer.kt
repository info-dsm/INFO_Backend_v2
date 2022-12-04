package com.info.info_v2_backend.company.adapter.input.event

import com.info.info_v2_backend.common.event.KafkaTopics
import com.info.info_v2_backend.common.file.dto.RegisterCompanyFileDto
import com.info.info_v2_backend.company.application.port.input.ChangeCompanyStatusUsecase
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class ChangeCompanyStatusConsumer(
    private val changeCompanyStatusUsecase: ChangeCompanyStatusUsecase
) {
    @KafkaListener(topics = [KafkaTopics.CHANGE_COMPANY_STATUS], groupId = "info", containerFactory = "stringChangeListener")
    fun registerCompanyFileDto(data: String) {
        changeCompanyStatusUsecase.change(data.substring(0, 12), data.substring(12).toInt())
    }

}