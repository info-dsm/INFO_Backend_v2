package com.info.info_v2_backend.company.application.port.input.preference

import com.info.info_v2_backend.company.domain.classification.CompanyClassification

interface SetCompanyClassificationPreferenceUsecase {

    fun set(userEmail: String, classification: CompanyClassification)
}
