package com.info.info_v2_backend.file.application.port.output.company

import com.info.info_v2_backend.common.file.dto.CompanyFileClassificationType

interface RemoveCompanyFilePort {

    fun remove(classificationType: CompanyFileClassificationType, companyNumber: String)
}