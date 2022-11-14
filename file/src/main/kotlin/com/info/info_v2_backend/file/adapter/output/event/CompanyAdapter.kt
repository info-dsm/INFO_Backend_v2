package com.info.info_v2_backend.file.adapter.output.event

import com.info.info_v2_backend.common.event.KafkaTopics
import com.info.info_v2_backend.common.file.RegisterCompanyFileDto
import com.info.info_v2_backend.file.application.port.output.RegisterCompanyFilePort
import com.info.info_v2_backend.file.domain.company.CompanyFile
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class CompanyAdapter(
    private val companyFileRegister: KafkaTemplate<String, RegisterCompanyFileDto>
): RegisterCompanyFilePort {

    override fun registerCompanyFile(file: RegisterCompanyFileDto) {
        companyFileRegister.send(KafkaTopics.REGISTER_COMPANY_FILE, file)
    }

}