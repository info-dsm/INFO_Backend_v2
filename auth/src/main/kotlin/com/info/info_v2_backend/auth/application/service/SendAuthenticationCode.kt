package com.info.info_v2_backend.auth.application.service

import com.info.info_v2_backend.auth.application.port.input.SendAuthenticationCodeUsecase
import com.info.info_v2_backend.auth.application.port.output.LoadUserByEmailPort
import com.info.info_v2_backend.auth.application.port.output.SaveCodePort
import com.info.info_v2_backend.auth.application.port.output.SendEmailPort
import com.info.info_v2_backend.auth.domain.Code
import com.info.info_v2_backend.common.auth.AuthenticationCodeType
import com.info.info_v2_backend.common.email.dto.SendEmailNotificationRequest
import org.apache.commons.lang.RandomStringUtils
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service

@Service
class SendAuthenticationCode(
    private val loadUserByEmailPort: LoadUserByEmailPort,
    private val saveAuthenticationCodePort: SaveCodePort,
    private val sendEmail: SendEmailPort
): SendAuthenticationCodeUsecase {

    override fun command(targetEmail: String, type: AuthenticationCodeType) {
        val user: UserDetails = loadUserByEmailPort.loadUserDetailsByUsername(targetEmail)

        val code = RandomStringUtils.randomNumeric(type.length)
        saveAuthenticationCodePort.save(
            Code(
                targetEmail,
                code,
                type.timeToLive,
                type
            )
        )

        val map = HashMap<String, String>()
        map.put("code", code)
        sendEmail.send(
            SendEmailNotificationRequest(
                targetEmail,
                "인증번호가 도착했습니다.",
                map
            )
        )

    }


}