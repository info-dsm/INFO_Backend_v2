package com.info.info_v2_backend.company.application.service.company

import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.common.exception.ErrorCode
import com.info.info_v2_backend.company.application.port.input.company.MakeAssociatedUsecase
import com.info.info_v2_backend.company.application.port.output.company.LoadCompanyPort
import com.info.info_v2_backend.company.application.port.output.company.SaveCompanyPort
import org.springframework.stereotype.Service

@Service
class MakeAssociated(
    private val loadCompanyPort: LoadCompanyPort,
    private val saveCompanyPort: SaveCompanyPort
): MakeAssociatedUsecase {
    override fun makeAssociated(companyNumber: String) {
        val company = loadCompanyPort.loadCompany(companyNumber)
            ?: throw BusinessException("회사를 찾지 못했습니다. -> $companyNumber", ErrorCode.PERSISTENCE_DATA_NOT_FOUND_ERROR)

        company.makeAssociated()
        saveCompanyPort.save(company)
    }


}
