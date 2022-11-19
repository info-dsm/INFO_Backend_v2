package com.info.info_v2_backend.file.application.port.input

import com.info.info_v2_backend.common.file.dto.response.CompanyFileResponse
import com.info.info_v2_backend.file.domain.company.CompanyFile

interface LoadCompanyFileUsecase {

    fun load(fileId: String): CompanyFile?
    fun loadByCompanyNumber(companyNumber: String): List<CompanyFileResponse>
}