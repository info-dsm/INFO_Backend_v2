package com.info.info_v2_backend.notice.application.port.output.technology

import com.info.info_v2_backend.notice.domain.technology.TechnologyUsage

interface SaveTechnologyUsagePort {

    fun save(technologyUsage: TechnologyUsage)
}