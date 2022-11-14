package com.info.info_v2_backend.company.application.port.output

import com.info.info_v2_backend.company.domain.Company

interface SaveCompanyPort {

    fun save(company: Company)
}