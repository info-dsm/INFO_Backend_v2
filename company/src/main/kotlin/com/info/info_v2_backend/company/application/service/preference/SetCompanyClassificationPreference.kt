package com.info.info_v2_backend.company.application.service.preference

import com.info.info_v2_backend.company.application.port.input.preference.SetCompanyClassificationPreferenceUsecase
import com.info.info_v2_backend.company.application.port.output.preference.SaveCompanyClassificationPreferencePort
import com.info.info_v2_backend.company.domain.classification.CompanyClassification
import com.info.info_v2_backend.company.domain.classification.CompanyClassificationPreference
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class SetCompanyClassificationPreference(
    private val saveCompanyClassificationPreferencePort: SaveCompanyClassificationPreferencePort
): SetCompanyClassificationPreferenceUsecase {
    override fun set(userEmail: String, classification: CompanyClassification) {
        saveCompanyClassificationPreferencePort.merge(
            CompanyClassificationPreference(
                classification,
                userEmail
            )
        )
    }


}
