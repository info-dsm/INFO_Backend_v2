package com.info.info_v2_backend.auth.application.port.output

import com.info.info_v2_backend.auth.domain.Code

interface LoadCodePort {

    fun load(email: String): Code
}