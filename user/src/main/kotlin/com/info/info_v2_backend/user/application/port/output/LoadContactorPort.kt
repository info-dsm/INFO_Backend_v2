package com.info.info_v2_backend.user.application.port.output

import com.info.info_v2_backend.user.domain.Contactor

interface LoadContactorPort {

    fun loadContactor(companyEmail: String): Contactor?
}