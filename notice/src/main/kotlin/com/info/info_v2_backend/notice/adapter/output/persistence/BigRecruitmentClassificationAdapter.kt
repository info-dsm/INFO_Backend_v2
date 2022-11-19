package com.info.info_v2_backend.notice.adapter.output.persistence

import com.info.info_v2_backend.notice.adapter.output.persistence.repository.RecruitmentBigClassificationRepository
import com.info.info_v2_backend.notice.adapter.output.persistence.repository.RecruitmentSmallClassificationRepository
import com.info.info_v2_backend.notice.application.port.output.bigClassification.LoadBigClassificationPort
import com.info.info_v2_backend.notice.application.port.output.bigClassification.SaveBigClassificationPort
import com.info.info_v2_backend.notice.domain.recruitmentBusiness.RecruitmentBigClassification
import org.springframework.stereotype.Service

@Service
class BigRecruitmentClassificationAdapter(
    private val bigClassificationRepository: RecruitmentBigClassificationRepository
): LoadBigClassificationPort, SaveBigClassificationPort {

    override fun loadBigClassification(bigClassification: String): RecruitmentBigClassification? {
        return bigClassificationRepository.findById(bigClassification).orElse(null)
    }

    override fun saveBigClassification(bigClassification: RecruitmentBigClassification): RecruitmentBigClassification {
        return bigClassificationRepository.save(bigClassification)
    }


}