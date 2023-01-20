package com.info.info_v2_backend.company.application.port.input.file

import com.info.info_v2_backend.common.file.dto.CompanyFileClassificationType
import com.info.info_v2_backend.common.file.dto.request.GenerateFileRequest
import com.info.info_v2_backend.common.file.dto.response.PresignedUrlResponse
import org.springframework.web.multipart.MultipartFile

interface AddCompanyFileUsecase {

    fun add(request: GenerateFileRequest, companyNumber: String, classificationType: CompanyFileClassificationType): PresignedUrlResponse
}