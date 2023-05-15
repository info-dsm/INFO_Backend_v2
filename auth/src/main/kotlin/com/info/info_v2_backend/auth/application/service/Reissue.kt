package com.info.info_v2_backend.auth.application.service

import com.info.info_v2_backend.auth.adapter.input.rest.dto.request.TokenReissueRequest
import com.info.info_v2_backend.auth.adapter.input.rest.dto.response.TokenResponse
import com.info.info_v2_backend.auth.application.port.input.ReissueUsecase
import com.info.info_v2_backend.auth.application.port.output.LoadCodePort
import com.info.info_v2_backend.auth.application.port.output.SaveCodePort
import com.info.info_v2_backend.auth.domain.Code
import com.info.info_v2_backend.common.auth.AuthenticationCodeType
import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.common.exception.ErrorCode
import com.info.info_v2_backend.common.auth.HeaderProperty
import org.springframework.stereotype.Service

@Service
class Reissue(
    private val saveCodePort: SaveCodePort,
    private val tokenProvider: TokenProvider,
    private val loadCodePort: LoadCodePort
): ReissueUsecase {
    override fun reissue(request: TokenReissueRequest): TokenResponse {
        val claims = tokenProvider.getClaims(request.refreshToken)
        val refreshToken = loadCodePort.load(claims.subject, AuthenticationCodeType.REFRESH).takeIf {
            it.type == AuthenticationCodeType.REFRESH.name
        }?: throw BusinessException("RefreshToken을 찾지 못했습니다.", ErrorCode.PERSISTENCE_DATA_NOT_FOUND_ERROR)
        if (refreshToken.data == request.refreshToken) {
            val dto = tokenProvider.encode(claims.subject, claims[HeaderProperty.COMPANY_NUMBER] as String?, claims[HeaderProperty.AUTH_LEVEL] as String)
            saveCodePort.save(
                Code(
                    claims.subject,
                    dto.refreshToken,
                    AuthenticationCodeType.REFRESH.timeToLive,
                    AuthenticationCodeType.REFRESH.name
                )
            )
            return dto
        } else throw BusinessException("RefreshToken값이 일치하지 않습니다.", ErrorCode.NOT_MATCHED_ERROR)
    }

}
