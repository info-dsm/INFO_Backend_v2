package com.info.info_v2_backend.user.application.service

import com.info.info_v2_backend.user.adapter.input.event.dto.ContactorDto
import com.info.info_v2_backend.user.adapter.input.event.dto.StudentDto
import com.info.info_v2_backend.user.adapter.input.event.dto.TeacherDto
import com.info.info_v2_backend.user.adapter.input.event.dto.UserDto
import com.info.info_v2_backend.user.application.port.`in`.SaveUserUsecase
import com.info.info_v2_backend.user.application.port.out.SaveUserPort
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class SaveUser(
    private val saveUserPort: SaveUserPort,
    private val encoder: PasswordEncoder
): SaveUserUsecase {
    override fun saveStudent(student: StudentDto) {
        saveUserPort.saveStudent(hashPassword(student) as StudentDto)
    }

    override fun saveTeacher(teacher: TeacherDto) {
        saveUserPort.saveTeacher(hashPassword(teacher) as TeacherDto)
    }

    override fun saveContactor(contactor: ContactorDto) {
        saveUserPort.saveContactor(hashPassword(contactor) as ContactorDto)
    }

    private fun hashPassword(user: UserDto): UserDto {
        val hashed = encoder.encode(user.password)
        user.hashPassword(hashed)
        return user
    }


}