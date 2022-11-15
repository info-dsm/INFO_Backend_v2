package com.info.info_v2_backend.company.application.port.output.businessArea

import com.info.info_v2_backend.company.domain.businessArea.BusinessAreaTagged

interface LoadBusinessAreaTaggedByCompanyNumberPort {

    fun load(companyNumber: String): List<BusinessAreaTagged>
}