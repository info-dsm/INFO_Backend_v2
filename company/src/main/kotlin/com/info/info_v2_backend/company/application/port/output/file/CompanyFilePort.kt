package com.info.info_v2_backend.company.application.port.output.file

import com.info.info_v2_backend.common.file.dto.CompanyFileClassificationType
import com.info.info_v2_backend.common.file.dto.response.CompanyFileResponse
import org.springframework.web.multipart.MultipartFile

interface CompanyFilePort {

    fun loadCompanyFile(companyNumber: String): List<CompanyFileResponse>
    fun upload(companyId: String, classification: CompanyFileClassificationType, file: MultipartFile)
    fun remove(companyNumber: String, fileId: String, classificationType: CompanyFileClassificationType)
}