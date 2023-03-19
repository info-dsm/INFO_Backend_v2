package com.info.info_v2_backend.file.application.port.output.company

import com.info.info_v2_backend.common.file.dto.response.CompanyFileResponse
import com.info.info_v2_backend.file.domain.File
import com.info.info_v2_backend.file.domain.company.CompanyFile

interface LoadCompanyFilePort {

    fun load(fileId: String): CompanyFile?
    fun loadPhotosByCompanyNumber(companyNumber: String): List<CompanyFile>
}