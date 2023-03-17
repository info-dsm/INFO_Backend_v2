package com.info.info_v2_backend.user.application.service

import com.info.info_v2_backend.user.application.port.input.ChangePasswordUsecase
import com.info.info_v2_backend.user.application.port.output.LoadUserPort
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ChangePassword(
    private val loadUserPort: LoadUserPort,
    private val encoder: PasswordEncoder
): ChangePasswordUsecase {
    @Transactional
    override fun change(email: String, password: String) {
        val user = loadUserPort.loadUser(email)
        user.changePassword(encoder.encode(password))
    }

}