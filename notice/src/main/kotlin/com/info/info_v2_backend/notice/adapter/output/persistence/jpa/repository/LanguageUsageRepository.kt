package com.info.info_v2_backend.notice.adapter.output.persistence.jpa.repository

import com.info.info_v2_backend.notice.domain.language.Language
import com.info.info_v2_backend.notice.domain.language.LanguageUsage
import org.springframework.data.jpa.repository.JpaRepository

interface LanguageUsageRepository: JpaRepository<LanguageUsage, String> {
}