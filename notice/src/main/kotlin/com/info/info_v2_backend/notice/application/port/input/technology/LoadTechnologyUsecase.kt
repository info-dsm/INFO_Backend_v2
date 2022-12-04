package com.info.info_v2_backend.notice.application.port.input.technology

import com.info.info_v2_backend.notice.adapter.input.rest.dto.response.technology.TechnologyResponse

interface LoadTechnologyUsecase {

    fun loadAll(): List<TechnologyResponse>
}