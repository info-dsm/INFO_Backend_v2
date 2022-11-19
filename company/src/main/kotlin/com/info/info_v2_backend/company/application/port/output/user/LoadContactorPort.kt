package com.info.info_v2_backend.company.application.port.output.user

import com.info.info_v2_backend.user.adapter.input.web.rest.dto.response.ContactorResponse

interface LoadContactorPort {

    fun loadContactor(companyNumber: String): ContactorResponse
}