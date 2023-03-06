package com.info.info_v2_backend.company.application.service

import com.info.info_v2_backend.company.application.port.input.CountCompanyUsecase
import com.info.info_v2_backend.company.application.port.output.company.LoadCompanyPort
import org.springframework.stereotype.Service


@Service
class CountCompany(
    private val loadCompanyPort: LoadCompanyPort
): CountCompanyUsecase {
    override fun count(): Int {
        return loadCompanyPort.count()
    }

}