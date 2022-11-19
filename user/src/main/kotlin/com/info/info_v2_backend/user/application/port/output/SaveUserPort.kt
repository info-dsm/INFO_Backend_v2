package com.info.info_v2_backend.user.application.port.output

import com.info.info_v2_backend.user.adapter.input.web.rest.dto.request.SaveContactorDto
import com.info.info_v2_backend.user.adapter.input.web.rest.dto.request.SaveStudentDto
import com.info.info_v2_backend.user.adapter.input.web.rest.dto.request.SaveTeacherDto

interface SaveUserPort {

    fun saveTeacher(dto: SaveTeacherDto)
    fun saveStudent(dto: SaveStudentDto)
    fun saveContactor(dto: SaveContactorDto)
}