package com.info.info_v2_backend.notice.application.service.technology

import com.info.info_v2_backend.notice.adapter.input.rest.dto.response.technology.TechnologyResponse
import com.info.info_v2_backend.notice.application.port.input.technology.LoadTechnologyUsecase
import com.info.info_v2_backend.notice.application.port.output.technology.LoadTechnologyPort
import org.springframework.stereotype.Service

@Service
class LoadTechnology(
    private val loadTechnologyPort: LoadTechnologyPort
): LoadTechnologyUsecase {
    override fun loadAll(): List<TechnologyResponse> {
        return loadTechnologyPort.loadAll().map {
            it.toTechnologyResponse()
        }
    }


}