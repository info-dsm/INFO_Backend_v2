package com.info.info_v2_backend.company.application.service

import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.common.exception.ErrorCode
import com.info.info_v2_backend.company.adapter.input.web.rest.dto.request.edit.EditCompanyRequest
import com.info.info_v2_backend.company.application.port.input.EditCompanyUsecase
import com.info.info_v2_backend.company.application.port.output.company.LoadCompanyPort
import com.info.info_v2_backend.company.application.port.output.company.SaveCompanyPort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class EditCompany(
    private val loadCompanyPort: LoadCompanyPort,
    private val saveCompanyPort: SaveCompanyPort
): EditCompanyUsecase {

    @Transactional
    override fun editCompany(companyNumber: String, request: EditCompanyRequest) {
        val company = loadCompanyPort.loadCompany(companyNumber)
            ?: throw BusinessException("회사를 조회하지 못했습니다. -> $companyNumber", ErrorCode.PERSISTENCE_DATA_NOT_FOUND_ERROR)
        company.editCompany(request)
    }


}