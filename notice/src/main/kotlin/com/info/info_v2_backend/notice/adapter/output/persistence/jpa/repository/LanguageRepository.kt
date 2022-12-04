package com.info.info_v2_backend.notice.adapter.output.persistence.jpa.repository

import com.info.info_v2_backend.notice.domain.language.Language
import org.springframework.data.jpa.repository.JpaRepository

interface LanguageRepository: JpaRepository<Language, String> {
}