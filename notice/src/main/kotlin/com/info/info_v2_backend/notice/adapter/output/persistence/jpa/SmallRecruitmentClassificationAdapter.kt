package com.info.info_v2_backend.notice.adapter.output.persistence.jpa

import com.info.info_v2_backend.notice.adapter.output.persistence.jpa.repository.RecruitmentSmallClassificationRepository
import com.info.info_v2_backend.notice.application.port.output.smallClassification.LoadSmallClassificationPort
import com.info.info_v2_backend.notice.application.port.output.smallClassification.SaveSmallClassificationPort
import com.info.info_v2_backend.notice.domain.recruitmentBusiness.RecruitmentSmallClassification
import com.mysql.cj.protocol.x.Notice
import org.springframework.stereotype.Service

@Service
class SmallRecruitmentClassificationAdapter(
    private val smallClassificationRepository: RecruitmentSmallClassificationRepository
): LoadSmallClassificationPort, SaveSmallClassificationPort {
    override fun loadSmallClassification(smallClassification: String): RecruitmentSmallClassification? {
        return smallClassificationRepository.findById(smallClassification).orElse(null)
    }

    override fun loadAll(): List<RecruitmentSmallClassification> {
        return smallClassificationRepository.findAll()
    }

    override fun saveSmallClassification(smallClassification: RecruitmentSmallClassification): RecruitmentSmallClassification {
        return smallClassificationRepository.save(smallClassification)
    }
}