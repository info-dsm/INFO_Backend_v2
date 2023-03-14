package com.info.info_v2_backend.user.application.service

import com.info.info_v2_backend.user.adapter.input.web.rest.dto.request.SaveContactorDto
import com.info.info_v2_backend.user.adapter.input.web.rest.dto.request.SaveStudentDto
import com.info.info_v2_backend.user.adapter.input.web.rest.dto.request.SaveTeacherDto
import com.info.info_v2_backend.user.adapter.input.web.rest.dto.request.SaveUserDto
import com.info.info_v2_backend.user.application.port.input.ChangePasswordUsecase
import com.info.info_v2_backend.user.application.port.input.SaveUserUsecase
import com.info.info_v2_backend.user.application.port.output.SaveUserPort
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class SaveUser(
    private val saveUserPort: SaveUserPort,
    private val encoder: PasswordEncoder
): SaveUserUsecase {
    override fun saveStudent(student: SaveStudentDto) {
        saveUserPort.saveStudent(hashPassword(student) as SaveStudentDto)
    }

    override fun saveTeacher(teacher: SaveTeacherDto) {
        saveUserPort.saveTeacher(hashPassword(teacher) as SaveTeacherDto)
    }

    override fun saveContactor(contactor: SaveContactorDto) {
        val contactor = hashPassword(contactor) as SaveContactorDto
        saveUserPort.saveContactor(contactor)
    }

    private fun hashPassword(user: SaveUserDto): SaveUserDto {
        val hashed = encoder.encode(user.password)
        user.hashPassword(hashed)
        return user
    }


}