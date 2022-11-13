package com.info.info_v2_backend.auth.application.port.input

import com.info.info_v2_backend.common.auth.AuthenticationCodeType

interface SendAuthenticationCodeUsecase {

    fun command(targetEmail: String, type: AuthenticationCodeType)
}