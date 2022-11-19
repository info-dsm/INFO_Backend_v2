package com.info.info_v2_backend.auth.application.port.output

import com.info.info_v2_backend.user.adapter.input.web.rest.dto.request.SaveStudentDto
import com.info.info_v2_backend.user.adapter.input.web.rest.dto.request.SaveTeacherDto

interface SaveUserPort {

    fun saveTeacherPort(teacher: SaveTeacherDto)
    fun saveStudentPort(student: SaveStudentDto)
}