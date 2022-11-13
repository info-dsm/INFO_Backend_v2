package com.info.info_v2_backend.user.adapter.input.event.dto

import com.info.info_v2_backend.user.domain.Teacher

data class TeacherDto(
    val name: String,
    val email: String,
    val hashedPassword: String
) {

    fun toTeacher(): Teacher {
        return Teacher(
            this.name,
            this.email,
            this.hashedPassword
        )
    }

}