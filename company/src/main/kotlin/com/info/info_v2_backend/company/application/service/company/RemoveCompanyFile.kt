package com.info.info_v2_backend.company.application.service.company

import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.common.exception.ErrorCode
import com.info.info_v2_backend.common.file.dto.CompanyFileClassificationType
import com.info.info_v2_backend.common.auth.HeaderProperty
import com.info.info_v2_backend.company.application.port.output.company.LoadCompanyPort
import com.info.info_v2_backend.company.application.port.output.file.CompanyFilePort
import com.info.info_v2_backend.company.application.port.input.file.RemoveCompanyFileUsecase
import org.springframework.stereotype.Service
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes

@Service
class RemoveCompanyFile(
    private val filePort: CompanyFilePort,
    private val loadCompanyPort: LoadCompanyPort
): RemoveCompanyFileUsecase {

    override fun remove(companyNumber: String, fileId: String, classificationType: CompanyFileClassificationType) {
        val headerCompany = HeaderProperty.COMPANY_NUMBER
        val company = loadCompanyPort.loadCompany(companyNumber)?: throw BusinessException("회사를 조회하지 못했습니다. -> $companyNumber", ErrorCode.PERSISTENCE_DATA_NOT_FOUND_ERROR)
        if (headerCompany != companyNumber || companyNumber != company.companyNumber) throw BusinessException("유효한 companyNumber가 아닙니다. -> $companyNumber", ErrorCode.INVALID_INPUT_DATA_ERROR)
        filePort.remove(
            (RequestContextHolder.getRequestAttributes() as ServletRequestAttributes).request.getHeader(
                headerCompany
            )?: throw BusinessException("토큰이 존재하지 않습니다.", ErrorCode.TOKEN_NEED_ERROR),
            fileId,
            classificationType
        )
    }

}
