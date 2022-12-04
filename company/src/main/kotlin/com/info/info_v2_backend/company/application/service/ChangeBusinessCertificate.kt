package com.info.info_v2_backend.company.application.service

import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.common.exception.ErrorCode
import com.info.info_v2_backend.common.file.dto.CompanyFileClassificationType
import com.info.info_v2_backend.common.auth.HeaderProperty
import com.info.info_v2_backend.company.application.port.input.file.ChangeCompanyFileUsecase
import com.info.info_v2_backend.company.application.port.output.company.LoadCompanyPort
import com.info.info_v2_backend.company.application.port.output.file.CompanyFilePort
import org.springframework.stereotype.Service
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import org.springframework.web.multipart.MultipartFile

@Service
class ChangeBusinessCertificate(
    private val companyFilePort: CompanyFilePort,
    private val loadCompanyPort: LoadCompanyPort,
): ChangeCompanyFileUsecase {
    override fun change(file: MultipartFile, companyNumber: String, classificationType: CompanyFileClassificationType) {
        val company = loadCompanyPort.loadCompany(companyNumber)?: throw BusinessException("회사를 찾지 못했습니다.", ErrorCode.PERSISTENCE_DATA_NOT_FOUND_ERROR)
        val requesterCompanyNumber = (RequestContextHolder.getRequestAttributes() as ServletRequestAttributes).request.getHeader(
            HeaderProperty.COMPANY_NUMBER) as String
        if (company.companyNumber != requesterCompanyNumber)
            throw BusinessException("요청자의 CompanyNumber가 해당 회사와 다릅니다. -> $requesterCompanyNumber", ErrorCode.NO_AUTHORIZATION_ERROR)
        companyFilePort.upload(
            companyNumber,
            CompanyFileClassificationType.BUSINESS_CERTIFICATE,
            file
        )
    }

}