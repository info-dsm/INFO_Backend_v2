package com.info.info_v2_backend.company.application.service

import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.common.exception.ErrorCode
import com.info.info_v2_backend.company.application.port.input.UpdateLastNoticedCompanyUsecase
import com.info.info_v2_backend.company.application.port.output.company.LoadCompanyPort
import org.springframework.stereotype.Service

@Service
class UpdateLastNoticedCompany(
    private val loadCompanyPort: LoadCompanyPort
): UpdateLastNoticedCompanyUsecase {

    override fun update(companyNumber: String) {
        val company = loadCompanyPort.loadCompany(companyNumber)
            ?: throw BusinessException("회사를 조회하지 못했습니다.", ErrorCode.PERSISTENCE_DATA_NOT_FOUND_ERROR)
        company.updateLastNoticeYear()

    }


}