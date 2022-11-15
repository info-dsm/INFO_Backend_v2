package com.info.info_v2_backend.company.application.port.output.businessArea

import com.info.info_v2_backend.company.domain.businessArea.BusinessArea

interface SaveBusinessAreaPort {

    fun saveBusinessArea(businessArea: BusinessArea): BusinessArea
}