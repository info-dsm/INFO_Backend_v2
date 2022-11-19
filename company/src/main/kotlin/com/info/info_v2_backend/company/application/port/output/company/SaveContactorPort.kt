package com.info.info_v2_backend.company.application.port.output.company

import com.info.info_v2_backend.user.adapter.input.web.rest.dto.request.SaveContactorDto

interface SaveContactorPort {

    fun save(saveContactorDto: SaveContactorDto)
}