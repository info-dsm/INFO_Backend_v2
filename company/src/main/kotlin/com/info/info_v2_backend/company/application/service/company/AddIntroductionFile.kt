package com.info.info_v2_backend.company.application.service.company

import com.info.info_v2_backend.common.file.FileConvert
import com.info.info_v2_backend.common.file.dto.CompanyFileClassificationType
import com.info.info_v2_backend.common.file.dto.request.GenerateFileRequest
import com.info.info_v2_backend.common.file.dto.response.PresignedUrlResponse
import com.info.info_v2_backend.company.application.port.input.file.AddCompanyFileUsecase
import com.info.info_v2_backend.company.application.port.output.company.LoadCompanyPort
import com.info.info_v2_backend.company.application.port.output.file.CompanyFilePort
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class AddIntroductionFile(
    private val companyFilePort: CompanyFilePort,
    private val loadCompanyPort: LoadCompanyPort
): AddCompanyFileUsecase {
    override fun add(request: GenerateFileRequest, companyNumber: String, classificationType: CompanyFileClassificationType): PresignedUrlResponse {
        val company = loadCompanyPort.loadCompany(companyNumber)
        return PresignedUrlResponse(
            companyFilePort.upload(
                companyNumber,
                classificationType,
                request
            ).url,
            request.fileName
        )
    }

}
