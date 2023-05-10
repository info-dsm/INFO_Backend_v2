package com.info.info_v2_backend.user.application.port.output

import com.info.info_v2_backend.common.user.TeacherDto
import com.info.info_v2_backend.user.domain.Teacher

interface LoadTeacherPort {

    fun load(userEmail: String): Teacher?
}
