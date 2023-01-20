package com.info.info_v2_backend.file.application.port.input.company

import com.info.info_v2_backend.common.file.dto.CompanyFileClassificationType
import com.info.info_v2_backend.common.file.dto.request.GenerateFileRequest
import com.info.info_v2_backend.common.file.dto.response.PresignedUrlResponse

interface UploadCompanyFileUsecase {

    fun uploadCompanyFile(request: GenerateFileRequest, classification: CompanyFileClassificationType, companyNumber: String): PresignedUrlResponse
}