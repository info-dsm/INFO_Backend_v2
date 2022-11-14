package com.info.info_v2_backend.user.adapter.input.event.dto

import com.info.info_v2_backend.user.domain.Student

class StudentDto(
    val studentKey: String,
    name: String,
    email: String,
    password: String,
    val githubLink: String
): UserDto(
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