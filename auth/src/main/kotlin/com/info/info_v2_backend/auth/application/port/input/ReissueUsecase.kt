package com.info.info_v2_backend.auth.application.port.input

import com.info.info_v2_backend.auth.adapter.input.rest.dto.request.TokenReissueRequest
import com.info.info_v2_backend.auth.adapter.input.rest.dto.response.TokenResponse

interface ReissueUsecase {

    fun reissue(request: TokenReissueRequest): TokenResponse
}