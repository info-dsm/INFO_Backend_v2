package com.info.info_v2_backend.user.application.port.input

import com.info.info_v2_backend.common.user.ContactorDto

interface LoadContactorUsecase {

    fun loadContactor(companyNumber: String): ContactorDto
}