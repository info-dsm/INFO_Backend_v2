package com.info.info_v2_backend.auth.application.port.input

interface StudentSignupUsecase {

    fun command(
        request: com.info.info_v2_backend.auth.adapter.input.rest.dto.request.StudentSignupRequest,
        emailAuthenticationCode: String
    )
}