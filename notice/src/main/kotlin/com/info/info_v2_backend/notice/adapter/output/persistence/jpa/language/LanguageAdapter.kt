package com.info.info_v2_backend.notice.adapter.output.persistence.jpa.language

import com.info.info_v2_backend.notice.adapter.output.persistence.jpa.repository.LanguageRepository
import com.info.info_v2_backend.notice.adapter.output.persistence.jpa.repository.LanguageUsageRepository
import com.info.info_v2_backend.notice.application.port.output.language.AddLanguagePort
import com.info.info_v2_backend.notice.application.port.output.language.LoadLanguagePort
import com.info.info_v2_backend.notice.application.port.output.language.SaveLanguageUsagePort
import com.info.info_v2_backend.notice.domain.language.Language
import com.info.info_v2_backend.notice.domain.language.LanguageUsage
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class LanguageAdapter(
    private val languageRepository: LanguageRepository,
    private val languageUsageRepository: LanguageUsageRepository
): LoadLanguagePort, SaveLanguageUsagePort, AddLanguagePort {
    override fun load(languageName: String): Language? {
        return languageRepository.findByIdOrNull(languageName)
    }

    override fun loadAll(): List<Language> {
        return languageRepository.findAll()
    }

    override fun save(languageUsage: LanguageUsage) {
        languageUsageRepository.save(languageUsage)
    }

    override fun add(language: Language) {
        languageRepository.save(language)
    }


}