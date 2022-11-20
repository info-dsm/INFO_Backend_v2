package com.info.info_v2_backend.file.application.port.output.company

import com.info.info_v2_backend.file.domain.File
import com.info.info_v2_backend.file.domain.company.CompanyFile

interface SaveCompanyFilePort {

    fun save(file: CompanyFile)
}