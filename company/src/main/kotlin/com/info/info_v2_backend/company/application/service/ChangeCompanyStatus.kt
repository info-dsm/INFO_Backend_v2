package com.info.info_v2_backend.company.application.service

import com.info.info_v2_backend.company.application.port.input.ChangeCompanyStatusUsecase
import com.info.info_v2_backend.company.application.port.output.company.LoadCompanyPort
import com.info.info_v2_backend.company.application.port.output.company.SaveCompanyPort
import org.springframework.stereotype.Service


@Service
class ChangeCompanyStatus(
    private val loadCompanyPort: LoadCompanyPort,
    private val saveCompanyPort: SaveCompanyPort
): ChangeCompanyStatusUsecase {

    override fun change(companyNumber: String, sequence: Int) {
        val company = loadCompanyPort.loadCompany(companyNumber)
            ?: return
        if (sequence == 1) {
            company.makeComplete()
        } else company.makeFailed()
        saveCompanyPort.save(company)
    }


}