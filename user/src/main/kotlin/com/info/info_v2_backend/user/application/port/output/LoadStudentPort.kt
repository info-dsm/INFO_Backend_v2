package com.info.info_v2_backend.user.application.port.output

import com.info.info_v2_backend.user.domain.Student

interface LoadStudentPort {

    fun loadStudent(studentEmail: String): Student?
    fun loadStudentListByGenerationAndClassNum(grade: Int, classNum: Int?): List<Student>
}
