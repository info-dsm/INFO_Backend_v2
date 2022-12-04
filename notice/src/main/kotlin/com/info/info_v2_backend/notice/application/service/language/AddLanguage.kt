package com.info.info_v2_backend.notice.application.service.language

import com.info.info_v2_backend.notice.application.port.input.language.AddLanguageUsecase
import com.info.info_v2_backend.notice.application.port.output.language.AddLanguagePort
import com.info.info_v2_backend.notice.domain.language.Language
import org.springframework.stereotype.Service

@Service
class AddLanguage(
    private val addLanguagePort: AddLanguagePort
): AddLanguageUsecase {

    override fun add(name: String) {
        addLanguagePort.add(
            Language(name)
        )
    }


}