package com.info.info_v2_backend.company.application.port.output

import com.info.info_v2_backend.user.adapter.input.event.dto.ContactorDto

interface SaveContactorPort {

    fun save(contactorDto: ContactorDto)
}