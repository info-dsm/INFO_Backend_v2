package com.info.info_v2_backend.company.application.port.input.file

import com.info.info_v2_backend.common.file.dto.CompanyFileClassificationType

interface RemoveCompanyFileUsecase {

    fun remove(companyId: String, fileId: String, classificationType: CompanyFileClassificationType)
}
