package com.info.info_v2_backend.auth.application.service

import com.info.info_v2_backend.auth.adapter.input.rest.dto.request.CheckTokenExpiredTimeRequest
import com.info.info_v2_backend.auth.application.port.input.CheckTokenExpiredTimeUsecase
import org.springframework.stereotype.Service

@Service
class CheckTokenExpiredTime(
    private val tokenProvider: TokenProvider
): CheckTokenExpiredTimeUsecase {

    override fun checkTokenExpiredTime(request: CheckTokenExpiredTimeRequest): Int {
        return tokenProvider.getLeastExpiredTime(request.token)
    }


}