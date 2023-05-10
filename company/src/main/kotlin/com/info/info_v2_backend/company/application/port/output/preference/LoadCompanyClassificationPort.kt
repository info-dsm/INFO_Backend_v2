package com.info.info_v2_backend.company.application.port.output.preference

import com.info.info_v2_backend.company.domain.classification.CompanyClassificationPreference

interface LoadCompanyClassificationPort {
    fun loadByUseremail(userEmail: String): CompanyClassificationPreference?
}
