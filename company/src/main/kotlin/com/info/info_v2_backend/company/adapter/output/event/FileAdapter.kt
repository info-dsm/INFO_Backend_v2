package com.info.info_v2_backend.company.adapter.output.event

import com.info.info_v2_backend.common.event.KafkaTopics
import com.info.info_v2_backend.common.file.RegisterCompanyFileDto
import com.info.info_v2_backend.common.file.UploadCompanyFileDto
import com.info.info_v2_backend.company.application.port.output.UploadFilePort
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class FileAdapter(
    private val fileSender: KafkaTemplate<String, UploadCompanyFileDto>
): UploadFilePort {

    override fun upload(file: UploadCompanyFileDto) {
        fileSender.send(KafkaTopics.UPLOAD_COMPANY_FILE, file)
    }
}