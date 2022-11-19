package com.info.info_v2_backend.user.adapter.input.web.rest.dto.request

import com.info.info_v2_backend.user.domain.Teacher


class SaveTeacherDto(
    name: String,
    email: String,
    password: String
): SaveUserDto(
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