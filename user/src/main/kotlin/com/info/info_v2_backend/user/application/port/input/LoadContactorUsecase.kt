package com.info.info_v2_backend.user.application.port.input

import com.info.info_v2_backend.user.adapter.input.web.rest.dto.response.ContactorResponse

interface LoadContactorUsecase {

    fun loadContactor(companyNumber: String): ContactorResponse
}