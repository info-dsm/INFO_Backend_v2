package com.info.info_v2_backend.employment.application.port.output

import com.info.info_v2_backend.common.applies.AppliesDto
import com.info.info_v2_backend.common.user.StudentDto

interface LoadUserPort {

    fun loadStudent(studentEmail: String): StudentDto?
}
