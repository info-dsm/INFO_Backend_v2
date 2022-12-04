package com.info.info_v2_backend.notice.application.port.output.language

import com.info.info_v2_backend.notice.domain.language.LanguageUsage

interface SaveLanguageUsagePort {

    fun save(languageUsage: LanguageUsage)
}