package com.info.info_v2_backend.company.adapter.input.event

import com.info.info_v2_backend.common.event.KafkaTopics
import com.info.info_v2_backend.common.file.RegisterCompanyFileDto
import com.info.info_v2_backend.company.application.port.input.RegisterCompanyFileDtoUsecase
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class CompanyConsumer(
    private val registerCompanyFileDtoUsecase: RegisterCompanyFileDtoUsecase
) {

    @KafkaListener(topics = [KafkaTopics.REGISTER_COMPANY_FILE], groupId = "info", containerFactory = "registerCompanyFileDtoChangeListener")
    fun registerCompanyFileDto(file: RegisterCompanyFileDto) {
        registerCompanyFileDtoUsecase.registerCompanyFile(file)
    }


}