package com.info.info_v2_backend.notice.adapter.output.persistence.jpa.technology

import com.info.info_v2_backend.notice.adapter.output.persistence.jpa.repository.TechnologyRepository
import com.info.info_v2_backend.notice.application.port.output.technology.LoadTechnologyPort
import com.info.info_v2_backend.notice.domain.technology.Technology
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class TechnologyAdapter(
    private val technologyRepository: TechnologyRepository
): LoadTechnologyPort {
    override fun load(technologyName: String): Technology? {
        return technologyRepository.findByIdOrNull(technologyName)
    }

    override fun loadAll(): List<Technology> {
        return technologyRepository.findAll()
    }
}