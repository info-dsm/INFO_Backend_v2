package com.info.info_v2_backend.notice.adapter.output.persistence.jpa.technology

import com.info.info_v2_backend.notice.adapter.output.persistence.jpa.repository.TechnologyUsageRepository
import com.info.info_v2_backend.notice.application.port.output.technology.RemoveTechnologyUsagePort
import com.info.info_v2_backend.notice.application.port.output.technology.SaveTechnologyUsagePort
import com.info.info_v2_backend.notice.domain.technology.TechnologyUsage
import org.springframework.stereotype.Service

@Service
class TechnologyUsageAdapter(
    private val technologyUsageRepository: TechnologyUsageRepository
): SaveTechnologyUsagePort, RemoveTechnologyUsagePort {
    override fun save(technologyUsage: TechnologyUsage) {
        technologyUsageRepository.save(technologyUsage)
    }

    override fun removeByNoticeId(noticeId: String) {
        technologyUsageRepository.deleteByNotice(noticeId)
    }


}