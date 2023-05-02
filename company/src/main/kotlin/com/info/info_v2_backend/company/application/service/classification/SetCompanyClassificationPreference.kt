package com.info.info_v2_backend.company.application.service.classification

import com.info.info_v2_backend.company.application.port.input.classification.SetCompanyClassificationPreferenceUsecase
import com.info.info_v2_backend.company.application.port.output.classifiction.SaveCompanyClassificationPreferencePort
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
