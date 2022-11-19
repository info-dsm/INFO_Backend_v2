package com.info.info_v2_backend.auth.application.port.input

import com.info.info_v2_backend.common.user.SaveStudentDto

interface StudentSignupUsecase {

    fun command(
        request: SaveStudentDto,
        emailAuthenticationCode: String
    )
}