package com.info.info_v2_backend.company.application.port.output.businessArea

import com.info.info_v2_backend.company.domain.businessArea.BusinessArea

interface LoadBusinessAreaPort {

    fun loadBusinessArea(name: String): BusinessArea?
    fun loadAll(): List<BusinessArea>
}