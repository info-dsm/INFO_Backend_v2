package com.info.info_v2_backend.user.application.service

import com.info.info_v2_backend.user.application.port.input.LoadPasswordHintUsecase
import com.info.info_v2_backend.user.application.port.output.LoadUserPort
import org.springframework.stereotype.Service

@Service
class LoadPasswordHint(
    private val loadUserPort: LoadUserPort
): LoadPasswordHintUsecase {
    override fun load(email: String): String? {
        return loadUserPort.loadUser(email).passwordHint
    }
}