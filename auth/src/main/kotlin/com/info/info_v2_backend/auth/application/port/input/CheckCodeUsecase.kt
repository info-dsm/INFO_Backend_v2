package com.info.info_v2_backend.auth.application.port.input

import com.info.info_v2_backend.common.auth.AuthenticationCodeDto

interface CheckCodeUsecase {

    fun check(dto: AuthenticationCodeDto): Boolean
}