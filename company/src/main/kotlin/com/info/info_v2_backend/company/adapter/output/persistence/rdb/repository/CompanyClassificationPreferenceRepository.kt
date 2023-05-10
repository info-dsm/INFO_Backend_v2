package com.info.info_v2_backend.company.adapter.output.persistence.rdb.repository

import com.info.info_v2_backend.company.domain.classification.CompanyClassificationPreference
import com.info.info_v2_backend.company.domain.classification.CompanyClassificationPreferenceIdClass
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface CompanyClassificationPreferenceRepository: JpaRepository<CompanyClassificationPreference, CompanyClassificationPreferenceIdClass> {

    fun findByUserEmail(userEmail: String): Optional<CompanyClassificationPreference>
    fun deleteByUserEmail(userEmail: String)
}
