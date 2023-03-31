package com.info.info_v2_backend.auth.application.service

import com.info.info_v2_backend.auth.application.port.input.SendAuthenticationCodeUsecase
import com.info.info_v2_backend.auth.application.port.output.userFeignPort.LoadUserDetailsPort
import com.info.info_v2_backend.auth.application.port.output.SaveCodePort
import com.info.info_v2_backend.auth.application.port.output.SendEmailPort
import com.info.info_v2_backend.auth.domain.Code
import com.info.info_v2_backend.common.auth.AuthenticationCodeType
import com.info.info_v2_backend.common.email.dto.SendEmailNotificationRequest
import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.common.exception.ErrorCode
import org.apache.commons.lang.RandomStringUtils
import org.springframework.stereotype.Service

@Service
class SendAuthenticationCode(
    private val loadUserByEmailPort: LoadUserDetailsPort,
    private val saveAuthenticationCodePort: SaveCodePort,
    private val sendEmail: SendEmailPort
): SendAuthenticationCodeUsecase {

    override fun send(targetEmail: String, type: AuthenticationCodeType) {
        try {
            loadUserByEmailPort.loadUserDetailsByUsername(targetEmail)?: throw BusinessException("", ErrorCode.PERSISTENCE_DATA_NOT_FOUND_ERROR)
            throw BusinessException(errorCode = ErrorCode.ALREADY_EXISTS_ERROR)
        } catch (e: BusinessException) {
            if (e.errorCode == ErrorCode.PERSISTENCE_DATA_NOT_FOUND_ERROR) {
                val code = RandomStringUtils.randomNumeric(type.length)
                saveAuthenticationCodePort.save(
                    Code(
                        targetEmail,
                        code,
                        type.timeToLive,
                        type.name
                    )
                )

                val map = HashMap<String, String>()
                map["code"] = code
                sendEmail.send(
                    SendEmailNotificationRequest(
                        targetEmail,
                        "인증번호가 도착했습니다.",
                        map
                    )
                )
            } else throw e
        }
    }


}