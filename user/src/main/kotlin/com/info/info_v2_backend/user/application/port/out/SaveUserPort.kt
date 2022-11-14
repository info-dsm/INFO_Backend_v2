package com.info.info_v2_backend.user.application.port.out

import com.info.info_v2_backend.user.adapter.input.event.dto.ContactorDto
import com.info.info_v2_backend.user.adapter.input.event.dto.StudentDto
import com.info.info_v2_backend.user.adapter.input.event.dto.TeacherDto

interface SaveUserPort {

    fun saveTeacher(dto: TeacherDto)
    fun saveStudent(dto: StudentDto)
    fun saveContactor(dto: ContactorDto)
}