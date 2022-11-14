package com.info.info_v2_backend.file.adapter.input.event

import com.info.info_v2_backend.common.event.KafkaTopics
import com.info.info_v2_backend.common.file.CompanyFileClassificationType
import com.info.info_v2_backend.common.file.FileDto
import com.info.info_v2_backend.common.file.UploadCompanyFileDto
import com.info.info_v2_backend.file.application.port.input.UploadCompanyFileUsecase
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class CompanyFileConsumer(
    private val uploadCompanyFileUsecase: UploadCompanyFileUsecase
) {

    @KafkaListener(topics = [KafkaTopics.UPLOAD_COMPANY_FILE], groupId = "info", containerFactory = "uploadCompanyFileDtoChangeListener")
    fun uploadCompanyFile(
        dto: UploadCompanyFileDto
    ) {
        uploadCompanyFileUsecase.uploadCompanyFile(
            dto.file,
            dto.classification,
            dto.companyId
        )
    }

}