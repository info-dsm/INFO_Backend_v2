package com.info.info_v2_backend.company.application.port.output

import com.info.info_v2_backend.common.auth.AuthenticationCodeDto
import com.info.info_v2_backend.common.auth.AuthenticationCodeType

interface CheckEmailCodePort {

    fun check(request: AuthenticationCodeDto): Boolean
}