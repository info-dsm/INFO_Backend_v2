package com.info.info_v2_backend.user.application.port.output

import com.info.info_v2_backend.common.user.StudentDto
import com.info.info_v2_backend.user.domain.Student

interface LoadStudentPort {

    fun loadStudent(studentEmail: String): Student?
    fun loadStudentListByGeneration(generation: Int): List<StudentDto>
}