package com.info.info_v2_backend.user.adapter.output.persistence

import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.common.exception.ErrorCode
import com.info.info_v2_backend.user.adapter.input.event.dto.ContactorDto
import com.info.info_v2_backend.user.adapter.input.event.dto.StudentDto
import com.info.info_v2_backend.user.adapter.input.event.dto.TeacherDto
import com.info.info_v2_backend.user.adapter.output.persistence.repository.ContactorRepository
import com.info.info_v2_backend.user.adapter.output.persistence.repository.StudentRepository
import com.info.info_v2_backend.user.adapter.output.persistence.repository.TeacherRepository
import com.info.info_v2_backend.user.adapter.output.persistence.repository.UserRepository
import com.info.info_v2_backend.user.application.port.out.LoadUserPort
import com.info.info_v2_backend.user.application.port.out.SaveUserPort
import com.info.info_v2_backend.user.domain.User
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserPersistenceAdapter(
    private val teacherRepository: TeacherRepository,
    private val studentRepository: StudentRepository,
    private val userRepository: UserRepository,
    private val contactorRepository: ContactorRepository
): SaveUserPort, LoadUserPort {
    override fun saveTeacher(dto: TeacherDto) {
        teacherRepository.save(
            dto.toTeacher()
        )
    }

    override fun saveStudent(dto: StudentDto) {
        studentRepository.save(
            dto.toStudent()
        )
    }

    override fun saveContactor(dto: ContactorDto) {
        contactorRepository.save(
            dto.toContactor()
        )
    }

    override fun load(userEmail: String): User {
        return userRepository.findByEmail(userEmail).orElse(null)
            ?: throw BusinessException(
                "User Not Found -> ${userEmail}",
                ErrorCode.PERSISTENCE_DATA_NOT_FOUND_ERROR
            )
    }


}