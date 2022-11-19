package com.info.info_v2_backend.company.application.port.input.file

import com.info.info_v2_backend.common.file.dto.RegisterCompanyFileDto

interface RegisterCompanyFileUsecase {

    fun register(dto: RegisterCompanyFileDto)
}