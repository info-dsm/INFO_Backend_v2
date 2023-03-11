package com.info.info_v2_backend.auth.application.service

import com.info.info_v2_backend.auth.adapter.input.rest.dto.request.ChangePasswordRequest
import com.info.info_v2_backend.auth.application.port.input.ChangePasswordUsecase
import com.info.info_v2_backend.auth.application.port.input.CheckCodeUsecase
import com.info.info_v2_backend.common.auth.AuthenticationCodeDto
import com.info.info_v2_backend.common.auth.AuthenticationCodeType
import com.info.info_v2_backend.user.application.port.output.LoadUserPort
import com.info.info_v2_backend.user.application.port.output.SaveUserPort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ChangePassword (
    private val checkCodeUsecase: CheckCodeUsecase,
    private val loadUserPort: LoadUserPort
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
            val user = loadUserPort.loadUser(request.email)
            user.changePassword(request.newPassword)
        }


    }


}