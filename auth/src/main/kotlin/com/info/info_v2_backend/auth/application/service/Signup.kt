package com.info.info_v2_backend.auth.application.service

import com.info.info_v2_backend.auth.application.port.input.StudentSignupUsecase
import com.info.info_v2_backend.auth.application.port.input.TeacherSignupUsecase
import com.info.info_v2_backend.auth.application.port.output.LoadCodePort
import com.info.info_v2_backend.auth.application.port.output.SaveUserPort
import com.info.info_v2_backend.common.auth.AuthenticationCodeType
import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.common.exception.ErrorCode
import com.info.info_v2_backend.common.user.SaveStudentDto
import com.info.info_v2_backend.user.adapter.input.web.rest.dto.request.SaveTeacherDto
import org.springframework.stereotype.Service

@Service
class Signup(
    private val saveUserPort: SaveUserPort,
    private val loadAuthenticationCodePort: LoadCodePort
): StudentSignupUsecase, TeacherSignupUsecase {

    override fun command(request: SaveStudentDto, emailAuthenticationCode: String) {
        if (authenticateCode(request.email, AuthenticationCodeType.SIGNUP_EMAIL, emailAuthenticationCode)) {
            saveUserPort.saveStudentPort(
                request
            )
        } else throw BusinessException("인증번호가 일치하지 않습니다. -> ${emailAuthenticationCode}", ErrorCode.NOT_MATCHED_ERROR)
    }

    override fun command(request: SaveTeacherDto, emailAuthenticationCode: String, teacherCode: String) {
        if (authenticateCode(request.email, AuthenticationCodeType.SIGNUP_EMAIL, emailAuthenticationCode)) {
            if (authenticateCode(request.email, AuthenticationCodeType.TEACHER, teacherCode)) {
                return saveUserPort.saveTeacherPort(
                    request
                )
            }
        }
        throw BusinessException("인증번호가 일치하지 않습니다. -> ${emailAuthenticationCode}", ErrorCode.NOT_MATCHED_ERROR)
    }

    private fun authenticateCode(email: String, type: AuthenticationCodeType, code: String): Boolean {
        return ((loadAuthenticationCodePort.load(
            email
        ).takeIf { it.type == type })
            ?: throw BusinessException(
                "인증번호를 조회하지 못했습니다. -> ${email}",
                ErrorCode.NO_DATA_FOUND_ERROR
            )).data == code
    }


}