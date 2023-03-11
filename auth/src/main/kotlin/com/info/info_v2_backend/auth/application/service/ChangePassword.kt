package com.info.info_v2_backend.auth.application.service

import com.info.info_v2_backend.auth.adapter.input.rest.dto.request.ChangePasswordRequest
import com.info.info_v2_backend.auth.application.port.input.ChangePasswordUsecase
import com.info.info_v2_backend.auth.application.port.input.CheckCodeUsecase
import com.info.info_v2_backend.auth.application.port.output.userFeignPort.ChangePasswordPort
import com.info.info_v2_backend.common.auth.AuthenticationCodeDto
import com.info.info_v2_backend.common.auth.AuthenticationCodeType
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ChangePassword (
    private val checkCodeUsecase: CheckCodeUsecase,
    private val changePasswordPort: ChangePasswordPort
): ChangePasswordUsecase {
    @Transactional
    override fun change(request: ChangePasswordRequest) {
        if (checkCodeUsecase.check(
            AuthenticationCodeDto(
                request.email,
                request.code,
                AuthenticationCodeType.CHANGE_PASSWORD
            )
        )) {
            changePasswordPort.change(request.email, request.newPassword)
        }


    }


}