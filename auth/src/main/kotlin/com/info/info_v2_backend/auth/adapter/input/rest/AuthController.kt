package com.info.info_v2_backend.auth.adapter.input.rest

import com.info.info_v2_backend.auth.adapter.input.rest.dto.request.LoginRequest
import com.info.info_v2_backend.auth.adapter.input.rest.dto.request.StudentSignupRequest
import com.info.info_v2_backend.auth.adapter.input.rest.dto.request.TeacherSignupRequest
import com.info.info_v2_backend.auth.adapter.input.rest.dto.response.TokenResponse
import com.info.info_v2_backend.auth.application.port.input.LoginUsecase
import com.info.info_v2_backend.auth.application.port.input.StudentSignupUsecase
import com.info.info_v2_backend.auth.application.port.input.TeacherSignupUsecase
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthController(
    private val loginUsecase: LoginUsecase,
    private val teacherSignupUsecase: TeacherSignupUsecase,
    private val studentSignupUsecase: StudentSignupUsecase
) {

    @PostMapping("/login")
    fun login(
        @RequestBody request: LoginRequest
    ): TokenResponse {
        return loginUsecase.command(request)
    }

    @PostMapping("/signup/student")
    fun studentSignup(
        @RequestBody request: StudentSignupRequest,
        @RequestParam(value = "emailCode") emailAuthenticationCode: String
    ) {
        studentSignupUsecase.command(request, emailAuthenticationCode)
    }

    @PostMapping("/signup/teacher")
    fun teacherSignup(
        @RequestBody request: TeacherSignupRequest,
        @RequestParam(value = "emailCode") emailAuthenticationCode: String,
        @RequestParam(value = "teacherCode") teacherCode: String
    ) {
         teacherSignupUsecase.command(request, emailAuthenticationCode, teacherCode)
    }

    @PutMapping("/reissue")
    fun reissue(): TokenResponse {

    }

    @PutMapping("/code")
    fun sendAuthenticationCode() {

    }






}