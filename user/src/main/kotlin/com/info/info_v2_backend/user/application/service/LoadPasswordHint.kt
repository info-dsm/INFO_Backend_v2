package com.info.info_v2_backend.user.application.service

import com.info.info_v2_backend.user.application.port.`in`.LoadPasswordHintUsecase
import com.info.info_v2_backend.user.application.port.out.LoadUserPort
import org.springframework.stereotype.Service

@Service
class LoadPasswordHint(
    private val loadUserPort: LoadUserPort
): LoadPasswordHintUsecase {
    override fun load(email: String): String? {
        return loadUserPort.load(email).passwordHint
    }
}