package com.info.info_v2_backend.company.application.port.input.file

import com.info.info_v2_backend.common.file.dto.CompanyFileClassificationType
import org.springframework.web.multipart.MultipartFile

interface ChangeCompanyFileUsecase {

    fun change(file: MultipartFile, companyNumber: String, classificationType: CompanyFileClassificationType)
}