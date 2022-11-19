package com.info.info_v2_backend.company.adapter.input.event

import com.info.info_v2_backend.common.event.KafkaTopics
import com.info.info_v2_backend.common.file.dto.RegisterCompanyFileDto
import com.info.info_v2_backend.company.application.port.input.file.RegisterCompanyFileUsecase
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class RegisterCompanyFileConsumer(
    private val registerCompanyFileUsecase: RegisterCompanyFileUsecase
) {

    @KafkaListener(topics = [KafkaTopics.REGISTER_COMPANY_FILE], groupId = "info", containerFactory = "registerCompanyFileDtoChangeListener")
    fun registerCompanyFileDto(dto: RegisterCompanyFileDto) {
        registerCompanyFileUsecase.register(dto)
    }
}