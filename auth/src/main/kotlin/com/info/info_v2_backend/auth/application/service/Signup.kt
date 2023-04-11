package com.info.info_v2_backend.auth.application.service

import com.info.info_v2_backend.auth.application.env.AuthenticationCodeProperty
import com.info.info_v2_backend.auth.application.port.input.StudentSignupUsecase
import com.info.info_v2_backend.auth.application.port.input.TeacherSignupUsecase
import com.info.info_v2_backend.auth.application.port.output.LoadCodePort
import com.info.info_v2_backend.auth.application.port.output.SaveUserPort
import com.info.info_v2_backend.common.auth.AuthenticationCodeType
import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.common.exception.ErrorCode
import com.info.info_v2_backend.user.adapter.input.web.rest.dto.request.SaveStudentDto
import com.info.info_v2_backend.user.adapter.input.web.rest.dto.request.SaveTeacherDto
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class Signup(
    private val saveUserPort: SaveUserPort,
    private val loadAuthenticationCodePort: LoadCodePort,
    private val authenticationCodeProperty: AuthenticationCodeProperty
): StudentSignupUsecase, TeacherSignupUsecase {
    private val log = LoggerFactory.getLogger(this.javaClass)

    override fun studentSignup(request: SaveStudentDto, emailAuthenticationCode: String) {
        if (authenticateCode(request.email, AuthenticationCodeType.SIGNUP_EMAIL, emailAuthenticationCode)) {
            saveUserPort.saveStudentPort(
                request
            )
        } else throw BusinessException("인증번호가 일치하지 않습니다. -> ${emailAuthenticationCode}", ErrorCode.NOT_MATCHED_ERROR)
    }

    override fun teacherSignup(request: SaveTeacherDto, emailAuthenticationCode: String, teacherCode: String) {
        if (authenticateCode(request.email, AuthenticationCodeType.SIGNUP_EMAIL, emailAuthenticationCode)) {
            if ((authenticationCodeProperty.teacherCode ?: 1111) == teacherCode) {
                return saveUserPort.saveTeacherPort(
                    request
                )
            } else throw BusinessException("선생 인증코드가 올바르지 않습니다.", ErrorCode.NOT_MATCHED_ERROR)
        }
        throw BusinessException("인증번호가 일치하지 않습니다. -> ${emailAuthenticationCode}", ErrorCode.NOT_MATCHED_ERROR)
    }

    private fun authenticateCode(email: String, type: AuthenticationCodeType, code: String): Boolean {
        return ((loadAuthenticationCodePort.load(
            email,
            type
        ).takeIf { it.type == type.name })?.data == code)
    }


}