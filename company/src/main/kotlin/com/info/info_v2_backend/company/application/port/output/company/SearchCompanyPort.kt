package com.info.info_v2_backend.company.application.port.output.company

import com.info.info_v2_backend.company.domain.Company
import com.info.info_v2_backend.company.domain.document.CompanyDocument
import org.springframework.data.domain.Page

interface SearchCompanyPort {

    fun search(idx: Int, size: Int, query: String): Page<Company>
}
