package com.info.info_v2_backend.user.application.port.`in`

import com.info.info_v2_backend.user.adapter.input.event.dto.ContactorDto
import com.info.info_v2_backend.user.adapter.input.event.dto.StudentDto
import com.info.info_v2_backend.user.adapter.input.event.dto.TeacherDto
import com.info.info_v2_backend.user.domain.User

interface SaveUserUsecase {

    fun saveStudent(student: StudentDto)
    fun saveTeacher(teacher: TeacherDto)
    fun saveContactor(contactor: ContactorDto)
}