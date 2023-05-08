package com.info.info_v2_backend.user.application.port.input

import com.info.info_v2_backend.common.user.TeacherDto

interface LoadTeacherUsecase {

    fun loadTeacher(userEmail: String): TeacherDto
}
