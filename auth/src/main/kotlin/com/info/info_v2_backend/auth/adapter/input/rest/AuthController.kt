package com.info.info_v2_backend.auth.adapter.input.rest

import com.info.info_v2_backend.auth.adapter.input.rest.dto.request.LoginCompanyRequest
import com.info.info_v2_backend.auth.adapter.input.rest.dto.request.LoginRequest
import com.info.info_v2_backend.auth.adapter.input.rest.dto.request.TokenReissueRequest
import com.info.info_v2_backend.auth.adapter.input.rest.dto.response.TokenResponse
import com.info.info_v2_backend.auth.application.port.input.*
import com.info.info_v2_backend.common.auth.AuthenticationCodeDto
import com.info.info_v2_backend.common.auth.AuthenticationCodeType
import com.info.info_v2_backend.user.adapter.input.web.rest.dto.request.SaveStudentDto
import com.info.info_v2_backend.user.adapter.input.web.rest.dto.request.SaveTeacherDto
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@Validated
class AuthController(
    private val loginUsecase: LoginUsecase,
    private val teacherSignupUsecase: TeacherSignupUsecase,
    private val studentSignupUsecase: StudentSignupUsecase,
    private val reissuePort: ReissueUsecase,
    private val sendCodeUsecase: SendAuthenticationCodeUsecase,
    private val checkCodeUsecase: CheckCodeUsecase,
) {

    @PostMapping("/login/user")
    @ResponseStatus(HttpStatus.CREATED)
    fun userLogin(
        @RequestBody request: LoginRequest
    ): TokenResponse {
        return loginUsecase.loginUser(request)
    }

    @PostMapping("/login/company")
    @ResponseStatus(HttpStatus.CREATED)
    fun companyLogin(
        @RequestBody request: LoginCompanyRequest
    ): TokenResponse {
        return loginUsecase.loginCompany(request)
    }

    @PostMapping("/signup/student")
    @ResponseStatus(HttpStatus.CREATED)
    fun studentSignup(
        @RequestBody request: SaveStudentDto,
        @RequestParam(value = "emailCode") emailAuthenticationCode: String
    ) {
        studentSignupUsecase.studentSignup(request, emailAuthenticationCode)
    }

    @PostMapping("/signup/teacher")
    @ResponseStatus(HttpStatus.CREATED)
    fun teacherSignup(
        @RequestBody request: SaveTeacherDto,
        @RequestParam(value = "emailCode") emailAuthenticationCode: String,
        @RequestParam(value = "teacherCode") teacherCode: String
    ) {
         teacherSignupUsecase.teacherSignup(request, emailAuthenticationCode, teacherCode)
    }

    @PutMapping("/reissue")
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun reissue(
        @RequestBody request: TokenReissueRequest
    ): TokenResponse {
        return reissuePort.reissue(request)
    }


    @PutMapping("/code")
    @ResponseStatus(HttpStatus.CREATED)
    fun sendSignupAuthenticationCode(
        @RequestParam email: String
    ) {
        sendCodeUsecase.send(
            email,
            AuthenticationCodeType.SIGNUP_EMAIL
        )
    }

    @PostMapping("/code")
    fun checkCode(@RequestBody request: AuthenticationCodeDto): Boolean {
        return checkCodeUsecase.check(request)
    }

    @PutMapping("/password")
    fun changePassword(

    ) {

    }


    //check Password Hint By
    //


}