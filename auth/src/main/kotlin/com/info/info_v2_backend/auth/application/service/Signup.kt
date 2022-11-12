package com.info.info_v2_backend.auth.application.service

import com.info.info_v2_backend.auth.adapter.input.rest.dto.request.StudentSignupRequest
import com.info.info_v2_backend.auth.adapter.input.rest.dto.request.TeacherSignupRequest
import com.info.info_v2_backend.auth.application.port.input.StudentSignupUsecase
import com.info.info_v2_backend.auth.application.port.input.TeacherSignupUsecase
import org.springframework.stereotype.Service

@Service
class Signup(

): StudentSignupUsecase, TeacherSignupUsecase {

    override fun command(request: StudentSignupRequest, emailAuthenticationCode: String) {
        TODO("Not yet implemented")
    }

    override fun command(request: TeacherSignupRequest, emailAuthenticationCode: String, teacherCode: String) {
        TODO("Not yet implemented")
    }


}