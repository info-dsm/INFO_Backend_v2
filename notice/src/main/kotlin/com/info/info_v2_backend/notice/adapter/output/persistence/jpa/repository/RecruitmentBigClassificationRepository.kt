package com.info.info_v2_backend.notice.adapter.output.persistence.jpa.repository

import com.info.info_v2_backend.notice.domain.recruitmentBusiness.RecruitmentBigClassification
import org.springframework.data.jpa.repository.JpaRepository

interface RecruitmentBigClassificationRepository: JpaRepository<RecruitmentBigClassification, String> {
}