package com.info.info_v2_backend.company.application.port.output.user

import com.info.info_v2_backend.common.user.ContactorDto
import com.info.info_v2_backend.common.user.StudentDto

interface LoadUserPort {

    fun loadContactor(companyNumber: String): ContactorDto?
    fun load(studentEmail: String): StudentDto?
}
