package com.info.info_v2_backend.company.application.service

import com.info.info_v2_backend.company.application.port.input.FailCompanyUsecase
import com.info.info_v2_backend.company.application.port.output.company.LoadCompanyPort
import com.info.info_v2_backend.company.application.port.output.company.SaveCompanyPort
import org.springframework.stereotype.Service

@Service
class FailCompany(
    private val loadCompanyPort: LoadCompanyPort,
    private val saveCompanyPort: SaveCompanyPort
): FailCompanyUsecase {
    override fun fail(companyId: String) {
        val company = loadCompanyPort.loadCompany(companyId)
            ?: return
        company.makeFailed()
        saveCompanyPort.save(company)
    }

}