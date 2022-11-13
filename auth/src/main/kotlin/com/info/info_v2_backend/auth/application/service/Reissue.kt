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
import org.springframework.stereotype.Service

@Service
class Reissue(
    private val saveCodePort: SaveCodePort,
    private val tokenProvider: TokenProvider,
    private val loadCodePort: LoadCodePort
): ReissueUsecase {
    override fun command(request: TokenReissueRequest): TokenResponse {
        val subject = tokenProvider.getSubjectWithExpiredCheck(request.accessToken)
        val refreshToken = loadCodePort.load(subject).takeIf {
            it.type == AuthenticationCodeType.REFRESH
        }?: throw BusinessException("RefreshToken을 찾지 못했습니다.", ErrorCode.PERSISTENCE_DATA_NOT_FOUND_ERROR)
        if (refreshToken.data == request.refreshToken) {
            val dto = tokenProvider.encode(subject)
            saveCodePort.save(
                Code(
                    subject,
                    dto.refreshToken,
                    AuthenticationCodeType.REFRESH.timeToLive,
                    AuthenticationCodeType.REFRESH
                )
            )
            return dto
        } else throw BusinessException("RefreshToken값이 일치하지 않습니다.", ErrorCode.NOT_MATCHED_ERROR)
    }

}