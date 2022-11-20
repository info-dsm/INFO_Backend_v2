package com.info.info_v2_backend.file.application.port.output.company

import com.info.info_v2_backend.common.file.dto.RegisterCompanyFileDto

interface RegisterCompanyFilePort {

    fun register(fileDto: RegisterCompanyFileDto)
}