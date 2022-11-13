package com.info.info_v2_backend.auth.adapter.input.rest

import com.info.info_v2_backend.auth.adapter.input.rest.dto.request.LoginRequest
import com.info.info_v2_backend.auth.adapter.input.rest.dto.request.TokenReissueRequest
import com.info.info_v2_backend.auth.adapter.input.rest.dto.response.TokenResponse
import com.info.info_v2_backend.auth.application.port.input.*
import com.info.info_v2_backend.common.auth.AuthenticationCodeType
import com.info.info_v2_backend.user.adapter.input.event.dto.StudentDto
import com.info.info_v2_backend.user.adapter.input.event.dto.TeacherDto
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthController(
    private val loginUsecase: LoginUsecase,
    private val teacherSignupUsecase: TeacherSignupUsecase,
    private val studentSignupUsecase: StudentSignupUsecase,
    private val reissuePort: ReissueUsecase,
    private val sendCodeUsecase: SendAuthenticationCodeUsecase
) {

    @PostMapping("/login")
    fun login(
        @RequestBody request: LoginRequest
    ): TokenResponse {
        return loginUsecase.command(request)
    }

    @PostMapping("/signup/student")
    fun studentSignup(
        @RequestBody request: StudentDto,
        @RequestParam(value = "emailCode") emailAuthenticationCode: String
    ) {
        studentSignupUsecase.command(request, emailAuthenticationCode)
    }

    @PostMapping("/signup/teacher")
    fun teacherSignup(
        @RequestBody request: TeacherDto,
        @RequestParam(value = "emailCode") emailAuthenticationCode: String,
        @RequestParam(value = "teacherCode") teacherCode: String
    ) {
         teacherSignupUsecase.command(request, emailAuthenticationCode, teacherCode)
    }

    @PutMapping("/reissue")
    fun reissue(
        @RequestBody request: TokenReissueRequest
    ): TokenResponse {
        return reissuePort.command(request)
    }

    @PutMapping("/code")
    fun sendSignupAuthenticationCode(
        @RequestParam email: String
    ) {
        sendCodeUsecase.command(
            email,
            AuthenticationCodeType.SIGNUP_EMAIL
        )
    }

}