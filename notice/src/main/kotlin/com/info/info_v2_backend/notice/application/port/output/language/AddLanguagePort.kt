package com.info.info_v2_backend.notice.application.port.output.language

import com.info.info_v2_backend.notice.domain.language.Language

interface AddLanguagePort {

    fun add(language: Language)
}