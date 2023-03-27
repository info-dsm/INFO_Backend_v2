package com.info.info_v2_backend.auth.adapter.output.persisetnece

import com.info.info_v2_backend.auth.adapter.output.persisetnece.repository.CodeRepository
import com.info.info_v2_backend.auth.application.port.output.LoadCodePort
import com.info.info_v2_backend.auth.application.port.output.RemoveCodePort
import com.info.info_v2_backend.auth.application.port.output.SaveCodePort
import com.info.info_v2_backend.auth.domain.Code
import com.info.info_v2_backend.common.auth.AuthenticationCodeType
import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.common.exception.ErrorCode
import org.springframework.stereotype.Service

@Service
class CodePersistenceAdapter(
    private val codeRepository: CodeRepository
): SaveCodePort, RemoveCodePort, LoadCodePort {

    override fun save(authenticationCode: Code) {
        codeRepository.save(authenticationCode)
    }

    override fun remove(targetEmail: String) {
        codeRepository.deleteByTargetEmail(targetEmail)
    }

    override fun load(email: String, type: AuthenticationCodeType): Code {
        return codeRepository.findByTargetEmailAndType(email, type)
            .orElse(null)?: throw BusinessException(
            "데이터를 조회할 수 없습니다. -> ${email}",
            ErrorCode.NO_DATA_FOUND_ERROR
            )
    }


}