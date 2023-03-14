package com.info.info_v2_backend.company.application.port.output.file

import com.info.info_v2_backend.common.file.dto.CompanyFileClassificationType
import com.info.info_v2_backend.common.file.dto.request.GenerateFileRequest
import com.info.info_v2_backend.common.file.dto.response.CompanyFileResponse
import com.info.info_v2_backend.common.file.dto.response.PresignedUrlResponse
import org.springframework.web.multipart.MultipartFile

interface CompanyFilePort {

    fun upload(companyId: String, classification: CompanyFileClassificationType, request: GenerateFileRequest): PresignedUrlResponse
    fun loadCompanyPhotoFile(companyNumber: String): List<CompanyFileResponse>
    fun remove(companyNumber: String, fileId: String, classificationType: CompanyFileClassificationType)
}