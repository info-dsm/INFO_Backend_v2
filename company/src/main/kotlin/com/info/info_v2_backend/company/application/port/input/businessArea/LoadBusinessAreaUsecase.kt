package com.info.info_v2_backend.company.application.port.input.businessArea

import com.info.info_v2_backend.company.domain.businessArea.BusinessArea

interface LoadBusinessAreaUsecase {

    fun loadAll(): List<BusinessArea>
}