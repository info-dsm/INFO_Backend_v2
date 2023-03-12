package com.info.info_v2_backend.user.adapter.input.web.rest.dto.request

import com.info.info_v2_backend.user.domain.Student
import com.info.info_v2_backend.user.domain.User


class SaveStudentDto(
    val studentKey: String,
    name: String,
    email: String,
    password: String,
    val githubLink: String?,
    val entranceYear: String
): SaveUserDto(
    name,
    email,
    password
) {

    fun toStudent(): Student {
        return Student(
            this.studentKey,
            this.name,
            this.email,
            this.password,
            this.githubLink
        )
    }
}