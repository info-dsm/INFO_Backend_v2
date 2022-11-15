package com.info.info_v2_backend.company.application.port.output.businessArea

import com.info.info_v2_backend.company.domain.businessArea.BusinessAreaTagged

interface SaveBusinessAreaTaggedPort {

    fun saveBusinessAreaTagged(businessAreaTagged: BusinessAreaTagged)
}