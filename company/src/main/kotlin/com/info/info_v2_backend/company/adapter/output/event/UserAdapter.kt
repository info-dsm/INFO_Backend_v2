package com.info.info_v2_backend.company.adapter.output.event

import com.info.info_v2_backend.company.application.port.output.SaveContactorPort
import com.info.info_v2_backend.user.adapter.input.event.dto.ContactorDto
import org.springframework.stereotype.Service

@Service
class UserAdapter: SaveContactorPort {

    override fun save(contactorDto: ContactorDto) {
        TODO("Not yet implemented")
    }
}