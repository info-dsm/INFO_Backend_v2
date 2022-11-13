package com.info.info_v2_backend.auth.application.port.input

import com.info.info_v2_backend.user.adapter.input.event.dto.StudentDto

interface StudentSignupUsecase {

    fun command(
        request: StudentDto,
        emailAuthenticationCode: String
    )
}