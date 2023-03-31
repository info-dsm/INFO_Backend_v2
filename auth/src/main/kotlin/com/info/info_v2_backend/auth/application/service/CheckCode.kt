package com.info.info_v2_backend.auth.application.service

import com.info.info_v2_backend.auth.application.port.input.CheckCodeUsecase
import com.info.info_v2_backend.auth.application.port.output.LoadCodePort
import com.info.info_v2_backend.common.auth.AuthenticationCodeDto
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class CheckCode(
    private val loadCodePort: LoadCodePort
): CheckCodeUsecase {
    private val log = LoggerFactory.getLogger(this.javaClass)

    override fun check(dto: AuthenticationCodeDto): Boolean {
        val code = loadCodePort.load(dto.email, dto.type)
        log.info("${code.identifyKey}, ${code.type}, ${code.data}, ${code.targetEmail}, ${code.timeToLive}")
        if (code.type == dto.type.name && code.data == dto.data) return true
        return false
    }

}