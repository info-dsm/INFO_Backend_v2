package com.info.info_v2_backend.notice.application.port.input.language

import com.info.info_v2_backend.notice.adapter.input.rest.dto.response.LanguageResponse

interface LoadLanguageUsecase {

    fun loadAll(): List<LanguageResponse>
}