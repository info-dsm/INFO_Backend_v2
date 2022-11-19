package com.info.info_v2_backend.file.application.port.input

import com.info.info_v2_backend.common.file.dto.CompanyFileClassificationType

interface RemoveCompanyFileUsecase {

    fun remove(companyNumber: String, fileId: String, classificationType: CompanyFileClassificationType)
}