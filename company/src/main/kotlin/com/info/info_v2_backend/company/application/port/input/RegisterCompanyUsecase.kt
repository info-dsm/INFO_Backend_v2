package com.info.info_v2_backend.company.application.port.input

import com.info.info_v2_backend.common.file.dto.response.PresignedUrlListResponse
import com.info.info_v2_backend.company.adapter.input.web.rest.dto.request.register.RegisterCompanyRequest

interface RegisterCompanyUsecase {

    fun register(
        request: RegisterCompanyRequest
    ): PresignedUrlListResponse

}