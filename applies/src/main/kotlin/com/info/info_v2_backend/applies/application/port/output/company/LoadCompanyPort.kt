package com.info.info_v2_backend.applies.application.port.output.company

import com.info.info_v2_backend.common.company.CompanyDto

interface LoadCompanyPort {

    fun loadCompany(companyNumber: String): CompanyDto?
}