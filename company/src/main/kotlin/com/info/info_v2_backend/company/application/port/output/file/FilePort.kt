package com.info.info_v2_backend.company.application.port.output.file

import com.info.info_v2_backend.common.file.CompanyFileClassificationType
import org.springframework.web.multipart.MultipartFile

interface FilePort {

    fun upload(companyId: String, classification: CompanyFileClassificationType , file: MultipartFile): String
    fun change(fileId: String, companyId: String, classification: CompanyFileClassificationType, file: MultipartFile): String
    fun remove(companyId: String, fileId: String)
}