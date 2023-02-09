package com.info.info_v2_backend.auth.application.port.input

import com.info.info_v2_backend.auth.adapter.input.rest.dto.request.CheckTokenExpiredTimeRequest

interface CheckTokenExpiredTimeUsecase {

    fun checkTokenExpiredTime(request: CheckTokenExpiredTimeRequest): Int
}