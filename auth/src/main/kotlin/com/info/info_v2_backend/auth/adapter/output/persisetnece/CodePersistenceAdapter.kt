package com.info.info_v2_backend.auth.adapter.output.persisetnece

import com.info.info_v2_backend.auth.adapter.output.persisetnece.repository.CodeRepository
import com.info.info_v2_backend.auth.application.port.output.LoadCodePort
import com.info.info_v2_backend.auth.application.port.output.RemoveCodePort
import com.info.info_v2_backend.auth.application.port.output.SaveCodePort
import com.info.info_v2_backend.auth.domain.Code
import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.common.exception.ErrorCode
import org.springframework.stereotype.Service

@Service
class CodePersistenceAdapter(
    private val authenticationCodeRepository: CodeRepository
): SaveCodePort, RemoveCodePort, LoadCodePort {

    override fun save(authenticationCode: Code) {
        authenticationCodeRepository.save(authenticationCode)
    }

    override fun remove(targetEmail: String) {
        authenticationCodeRepository.deleteByTargetEmail(targetEmail)
    }

    override fun load(email: String): Code {
        return authenticationCodeRepository.findByTargetEmail(email)
            .orElse(null)?: throw BusinessException(
            "인증번호를 조회할 수 없습니다. -> ${email}",
            ErrorCode.NO_DATA_FOUND_ERROR
            )
    }


}