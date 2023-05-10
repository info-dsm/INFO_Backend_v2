package com.info.info_v2_backend.company.application.service.company

import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.common.exception.ErrorCode
import com.info.info_v2_backend.company.application.port.input.company.MakeLeadingUsecase
import com.info.info_v2_backend.company.application.port.output.company.LoadCompanyPort
import com.info.info_v2_backend.company.application.port.output.company.SaveCompanyPort
import org.springframework.stereotype.Service

@Service
class MakeLeading(
    private val loadCompanyPort: LoadCompanyPort,
    private val saveCompanyPort: SaveCompanyPort
): MakeLeadingUsecase {
    override fun makeLeading(companyNumber: String) {
        val company = loadCompanyPort.loadCompany(companyNumber)
            ?: throw BusinessException(
                "기업을 조회할 수 없습니다.",
                ErrorCode.PERSISTENCE_DATA_NOT_FOUND_ERROR
            )
        company.makeLeading()
        saveCompanyPort.save(company)
    }

    override fun cancelLeading(companyNumber: String) {
        val company = loadCompanyPort.loadCompany(companyNumber)
            ?: throw BusinessException(
                errorCode = ErrorCode.PERSISTENCE_DATA_NOT_FOUND_ERROR
            )
        company.cancelLeading()
        saveCompanyPort.save(company)
    }
}
