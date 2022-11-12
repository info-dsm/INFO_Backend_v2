package com.info.info_v2_backend.auth.application.port.input

import com.info.info_v2_backend.auth.adapter.input.rest.dto.request.TeacherSignupRequest
import org.springframework.web.bind.annotation.RequestParam

interface TeacherSignupUsecase {

    fun command(request: TeacherSignupRequest, emailAuthenticationCode: String, teacherCode: String)
}