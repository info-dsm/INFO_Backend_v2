package com.info.info_v2_backend.user.adapter.input.event.dto

import com.info.info_v2_backend.user.domain.Student

data class StudentDto(
    val studentKey: String,
    val name: String,
    val email: String,
    val password: String,
    val githubLink: String
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