package com.info.info_v2_backend.user.application.port.input

import com.info.info_v2_backend.common.user.StudentDto


interface LoadStudentUsecase {

    fun loadStudent(studentEmail: String): StudentDto
    fun loadStudentListByGeneration(generation: Int): List<StudentDto>

}