package com.info.info_v2_backend.notice.application.port.output.language

import com.info.info_v2_backend.notice.domain.language.Language

interface LoadLanguagePort {

    fun load(languageName: String): Language?
    fun loadAll(): List<Language>
}