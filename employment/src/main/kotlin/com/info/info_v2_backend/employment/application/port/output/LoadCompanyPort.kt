package com.info.info_v2_backend.employment.application.port.output

import com.info.info_v2_backend.common.company.CompanyDto

interface LoadCompanyPort {

    fun loadCompany(companyNumber: String): CompanyDto?
}