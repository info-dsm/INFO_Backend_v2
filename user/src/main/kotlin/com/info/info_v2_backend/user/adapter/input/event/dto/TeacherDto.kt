package com.info.info_v2_backend.user.adapter.input.event.dto

import com.info.info_v2_backend.user.domain.Teacher

class TeacherDto(
    name: String,
    email: String,
    password: String
): UserDto(
    name,
    email,
    password
) {

    fun toTeacher(): Teacher {
        return Teacher(
            this.name,
            this.email,
            this.password
        )
    }

}