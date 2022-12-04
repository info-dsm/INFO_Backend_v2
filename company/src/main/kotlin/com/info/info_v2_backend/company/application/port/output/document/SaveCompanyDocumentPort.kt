package com.info.info_v2_backend.company.application.port.output.document

import com.info.info_v2_backend.company.domain.document.CompanyDocument

interface SaveCompanyDocumentPort {

    fun save(document: CompanyDocument)
}