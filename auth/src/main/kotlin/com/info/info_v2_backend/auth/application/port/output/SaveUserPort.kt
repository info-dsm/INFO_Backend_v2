package com.info.info_v2_backend.auth.application.port.output

import com.info.info_v2_backend.user.adapter.input.event.dto.StudentDto
import com.info.info_v2_backend.user.adapter.input.event.dto.TeacherDto

interface SaveUserPort {

    fun saveTeacherPort(teacher: TeacherDto)
    fun saveStudentPort(student: StudentDto)
}