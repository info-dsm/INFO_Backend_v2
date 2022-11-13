package com.info.info_v2_backend.auth.application.port.output

import com.info.info_v2_backend.auth.domain.Code

interface SaveCodePort {

    fun save(authenticationCode: Code)
}