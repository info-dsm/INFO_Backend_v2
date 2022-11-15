package com.info.info_v2_backend.company.application.port.output.company

import com.info.info_v2_backend.company.domain.Company

interface SaveCompanyPort {

    fun save(company: Company)
}