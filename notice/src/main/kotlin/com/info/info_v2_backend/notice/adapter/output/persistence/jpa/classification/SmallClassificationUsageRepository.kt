package com.info.info_v2_backend.notice.adapter.output.persistence.jpa.classification

import com.info.info_v2_backend.notice.domain.recruitmentBusiness.RecruitmentSmallClassificationUsage
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface SmallClassificationUsageRepository: JpaRepository<RecruitmentSmallClassificationUsage, String> {

    fun findByNoticeId(noticeId: String): List<RecruitmentSmallClassificationUsage>
}