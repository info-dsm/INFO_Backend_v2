package com.info.info_v2_backend.company.application.port.output.user

import com.info.info_v2_backend.common.user.ContactorDto

interface LoadContactorPort {

    fun loadContactor(companyNumber: String): ContactorDto?
}