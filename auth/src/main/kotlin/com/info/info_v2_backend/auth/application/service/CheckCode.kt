package com.info.info_v2_backend.auth.application.service

import com.info.info_v2_backend.auth.application.port.input.CheckCodeUsecase
import com.info.info_v2_backend.auth.application.port.output.LoadCodePort
import com.info.info_v2_backend.common.auth.AuthenticationCodeDto
import org.springframework.stereotype.Service

@Service
class CheckCode(
    private val loadCodePort: LoadCodePort
): CheckCodeUsecase {
    override fun check(dto: AuthenticationCodeDto): Boolean {
        val code = loadCodePort.load(dto.email)
        if (code.type == dto.type && code.data == dto.data) return true
        return false
    }

}