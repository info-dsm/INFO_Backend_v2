package com.info.info_v2_backend.file.application.service.company

import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.common.exception.ErrorCode
import com.info.info_v2_backend.common.file.dto.CompanyFileClassificationType
import com.info.info_v2_backend.common.file.dto.request.GenerateFileRequest
import com.info.info_v2_backend.common.file.dto.response.PresignedUrlResponse
import com.info.info_v2_backend.file.application.port.input.UploadFileUsecase
import com.info.info_v2_backend.file.application.port.input.company.UploadCompanyFileUsecase
import com.info.info_v2_backend.file.application.port.output.company.ChangeCompanyStatusPort
import com.info.info_v2_backend.file.application.port.output.company.RemoveCompanyFilePort
import com.info.info_v2_backend.file.application.port.output.company.SaveCompanyFilePort
import com.info.info_v2_backend.file.domain.company.CompanyFile
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service


@Service
class UploadCompanyFile(
    private val saveCompanyFilePort: SaveCompanyFilePort,
    private val removeCompanyFilePort: RemoveCompanyFilePort,
    private val changeCompanyStatusPort: ChangeCompanyStatusPort,
    private val uploadFileUsecase: UploadFileUsecase
): UploadCompanyFileUsecase {
    private val log = LoggerFactory.getLogger(this.javaClass)

    override fun uploadCompanyFile(
        request: GenerateFileRequest,
        classification: CompanyFileClassificationType,
        companyNumber: String
    ): PresignedUrlResponse {
        try {
            val dto = uploadFileUsecase.upload(request, "COMPANY/${companyNumber}", "${classification.name}")
            val companyFile = CompanyFile(
                dto,
                classification,
                companyNumber
            )
            if (classification == CompanyFileClassificationType.COMPANY_LOGO
                || classification == CompanyFileClassificationType.BUSINESS_CERTIFICATE
            ) {
                removeCompanyFilePort.remove(classification, companyNumber)
            }

            saveCompanyFilePort.save(
                companyFile
            )

            changeCompanyStatusPort.change(companyNumber, 1)
            return PresignedUrlResponse(
                dto.authenticatedUri?: throw BusinessException(errorCode = ErrorCode.UNDEFINED_ERROR),
                dto.fileName
            )
        } catch (e: BusinessException) {
            changeCompanyStatusPort.change(companyNumber, 2)
            log.warn(e.message)
            throw BusinessException(errorCode = ErrorCode.UNDEFINED_ERROR)
        }
    }


}
