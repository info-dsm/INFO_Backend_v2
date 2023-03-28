package com.info.info_v2_backend.auth.application.port.output

import com.info.info_v2_backend.auth.domain.Code
import com.info.info_v2_backend.common.auth.AuthenticationCodeType

interface LoadCodePort {

    fun load(email: String, type: AuthenticationCodeType): Code
}