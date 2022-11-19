package com.info.info_v2_backend.user.application.port.input

import com.info.info_v2_backend.user.adapter.input.web.rest.dto.request.SaveContactorDto
import com.info.info_v2_backend.user.adapter.input.web.rest.dto.request.SaveStudentDto
import com.info.info_v2_backend.user.adapter.input.web.rest.dto.request.SaveTeacherDto

interface SaveUserUsecase {

    fun saveStudent(student: SaveStudentDto)
    fun saveTeacher(teacher: SaveTeacherDto)
    fun saveContactor(contactor: SaveContactorDto)
}