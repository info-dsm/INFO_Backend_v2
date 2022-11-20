package com.info.info_v2_backend.notice.adapter.output.persistence.jpa.repository

import com.info.info_v2_backend.notice.domain.technology.TechnologyUsage
import com.info.info_v2_backend.notice.domain.technology.TechnologyUsageIdClass
import org.springframework.data.jpa.repository.JpaRepository

interface TechnologyUsageRepository: JpaRepository<TechnologyUsage, TechnologyUsageIdClass> {

    fun deleteByNotice(noticeId: String)
}