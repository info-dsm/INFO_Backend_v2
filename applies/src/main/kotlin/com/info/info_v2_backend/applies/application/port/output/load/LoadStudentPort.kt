package com.info.info_v2_backend.applies.application.port.output.load

import com.info.info_v2_backend.common.user.StudentDto


interface LoadStudentPort {

    fun loadStudent(studentEmail: String): StudentDto?
}