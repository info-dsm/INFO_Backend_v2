package com.info.info_v2_backend.auth.application.port.input

import com.info.info_v2_backend.user.adapter.input.web.rest.dto.request.SaveTeacherDto

interface TeacherSignupUsecase {

    fun command(request: SaveTeacherDto, emailAuthenticationCode: String, teacherCode: String)
}