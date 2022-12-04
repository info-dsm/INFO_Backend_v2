package com.info.info_v2_backend.notice.application.service.language

import com.info.info_v2_backend.notice.adapter.input.rest.dto.response.LanguageResponse
import com.info.info_v2_backend.notice.application.port.input.language.LoadLanguageUsecase
import com.info.info_v2_backend.notice.application.port.output.language.LoadLanguagePort
import org.springframework.stereotype.Service

@Service
class LoadLanguage(
    private val loadLanguagePort: LoadLanguagePort
): LoadLanguageUsecase {

    override fun loadAll(): List<LanguageResponse> {
        return loadLanguagePort.loadAll().map {
            it.toLanguageResponse()
        }
    }


}