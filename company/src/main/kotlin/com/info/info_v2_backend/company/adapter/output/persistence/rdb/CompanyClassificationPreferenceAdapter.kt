package com.info.info_v2_backend.company.adapter.output.persistence.rdb

import com.info.info_v2_backend.company.adapter.output.persistence.rdb.repository.CompanyClassificationPreferenceRepository
import com.info.info_v2_backend.company.application.port.output.preference.LoadCompanyClassificationPort
import com.info.info_v2_backend.company.application.port.output.preference.SaveCompanyClassificationPreferencePort
import com.info.info_v2_backend.company.domain.classification.CompanyClassificationPreference
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class CompanyClassificationPreferenceAdapter(
    val companyClassificationPreferenceRepository: CompanyClassificationPreferenceRepository
): LoadCompanyClassificationPort, SaveCompanyClassificationPreferencePort {
    override fun loadByUseremail(userEmail: String): CompanyClassificationPreference? {
        return companyClassificationPreferenceRepository.findByUserEmail(userEmail).orElse(null)
    }

    override fun merge(preference: CompanyClassificationPreference) {
        companyClassificationPreferenceRepository.findByUserEmail(preference.userEmail).orElse(null)?.let {
            companyClassificationPreferenceRepository.deleteByUserEmail(preference.userEmail)
        }
        companyClassificationPreferenceRepository.save(preference)

    }
}
