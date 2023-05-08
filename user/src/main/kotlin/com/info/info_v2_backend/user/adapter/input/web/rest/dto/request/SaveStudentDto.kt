package com.info.info_v2_backend.user.adapter.input.web.rest.dto.request

import com.info.info_v2_backend.user.domain.Student
import com.info.info_v2_backend.user.domain.User
import java.util.Random


class SaveStudentDto(
    val studentKey: String,
    name: String,
    email: String,
    password: String,
    val githubLink: String?
): SaveUserDto(
    name,
    email,
    password
) {
    private val random = Random()

    fun toStudent(): Student {
        return Student(
            this.studentKey,
            this.name,
            this.email,
            this.password,
            this.githubLink,
            random.nextInt(6)
        )
    }
}