package com.info.info_v2_backend.auth.adapter.input.rest.dto.request

import com.info.info_v2_backend.auth.adapter.input.rest.vaildation.SchoolEmail
import com.info.info_v2_backend.user.adapter.input.web.rest.dto.request.SaveStudentDto

class SaveStudentRequest(
    val studentKey: String,
    val name: String,
    @field:SchoolEmail
    val email: String,
    val password: String,
    val githubLink: String?,
    val entranceYear: Int
){

    fun toSaveStudentDto(): SaveStudentDto {
        return SaveStudentDto(
            this.studentKey,
            this.name,
            this.email,
            this.password,
            this.githubLink
        )
    }
}