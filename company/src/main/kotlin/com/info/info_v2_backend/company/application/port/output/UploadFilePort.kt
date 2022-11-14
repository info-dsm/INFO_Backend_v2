package com.info.info_v2_backend.company.application.port.output

import com.info.info_v2_backend.common.file.RegisterCompanyFileDto
import com.info.info_v2_backend.common.file.UploadCompanyFileDto

interface UploadFilePort {

    fun upload(file: UploadCompanyFileDto)
}