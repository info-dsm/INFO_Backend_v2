package com.info.info_v2_backend.company.application.port.output.classifiction

import com.info.info_v2_backend.company.domain.classification.CompanyClassificationPreference

interface LoadCompanyClassificationPort {
    fun loadByUseremail(userEmail: String): CompanyClassificationPreference?
}
