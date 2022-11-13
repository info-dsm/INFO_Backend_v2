package com.info.info_v2_backend.auth.application.service

import com.info.info_v2_backend.auth.adapter.input.rest.dto.request.TokenReissueRequest
import com.info.info_v2_backend.auth.adapter.input.rest.dto.response.TokenResponse
import com.info.info_v2_backend.auth.application.port.input.ReissueUsecase
import com.info.info_v2_backend.auth.application.port.output.SaveCodePort
import org.springframework.stereotype.Service

@Service
class Reissue(
    private val saveCodePort: SaveCodePort,
    private val tokenProvider: TokenProvider
): ReissueUsecase {
    override fun command(request: TokenReissueRequest): TokenResponse {
        val claims = tokenProvider.decodeBody(request.accessToken)
        

    }

}