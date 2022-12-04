package com.info.info_v2_backend.notice.adapter.output.persistence.jpa.classification

import com.info.info_v2_backend.notice.application.port.output.smallClassification.LoadSmallClassificationUsagePort
import com.info.info_v2_backend.notice.application.port.output.smallClassification.SaveSmallClassificationUsagePort
import com.info.info_v2_backend.notice.domain.recruitmentBusiness.RecruitmentSmallClassificationUsage
import org.springframework.stereotype.Service


@Service
class SmallClassificationUsageAdapter(
    private val smallClassificationUsageRepository: SmallClassificationUsageRepository,
): SaveSmallClassificationUsagePort, LoadSmallClassificationUsagePort {
    override fun save(smallClassificationUsage: RecruitmentSmallClassificationUsage) {
        smallClassificationUsageRepository.save(
            smallClassificationUsage
        )
    }

    override fun loadAllByNoticeId(noticeId: String): List<RecruitmentSmallClassificationUsage> {
        return smallClassificationUsageRepository.findByNoticeId(noticeId)
    }


}