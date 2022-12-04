package com.info.info_v2_backend.company.application.port.output.document

import com.info.info_v2_backend.company.domain.document.CompanyDocument
import org.springframework.data.domain.Page

interface SearchCompanyDocumentPort {

    fun search(idx: Int, size: Int, query: String): Page<CompanyDocument>
}