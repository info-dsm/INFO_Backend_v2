package com.info.info_v2_backend.auth.application.port.input

import com.info.info_v2_backend.user.adapter.input.event.dto.TeacherDto

interface TeacherSignupUsecase {

    fun command(request: TeacherDto, emailAuthenticationCode: String, teacherCode: String)
}