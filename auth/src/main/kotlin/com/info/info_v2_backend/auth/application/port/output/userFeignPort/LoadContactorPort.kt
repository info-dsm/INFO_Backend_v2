package com.info.info_v2_backend.auth.application.port.output.userFeignPort

import com.info.info_v2_backend.common.user.ContactorDto
import com.info.info_v2_backend.user.adapter.input.web.rest.dto.response.CommonUserDetails

interface LoadContactorPort {

    fun load(companyNumber: String): ContactorDto?
}