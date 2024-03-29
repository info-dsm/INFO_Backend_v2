package com.info.info_v2_backend.user.adapter.output.persistence

import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.common.exception.ErrorCode
import com.info.info_v2_backend.user.adapter.input.web.rest.dto.request.SaveContactorDto
import com.info.info_v2_backend.user.adapter.input.web.rest.dto.request.SaveStudentDto
import com.info.info_v2_backend.user.adapter.input.web.rest.dto.request.SaveTeacherDto
import com.info.info_v2_backend.user.adapter.output.persistence.repository.ContactorRepository
import com.info.info_v2_backend.user.adapter.output.persistence.repository.StudentRepository
import com.info.info_v2_backend.user.adapter.output.persistence.repository.TeacherRepository
import com.info.info_v2_backend.user.adapter.output.persistence.repository.UserRepository
import com.info.info_v2_backend.user.application.port.output.*
import com.info.info_v2_backend.user.domain.Contactor
import com.info.info_v2_backend.user.domain.Student
import com.info.info_v2_backend.user.domain.Teacher
import com.info.info_v2_backend.user.domain.User
import org.springframework.stereotype.Service

@Service
class UserPersistenceAdapter(
    private val teacherRepository: TeacherRepository,
    private val studentRepository: StudentRepository,
    private val userRepository: UserRepository,
    private val contactorRepository: ContactorRepository,
): SaveUserPort, LoadUserPort, LoadContactorPort, LoadStudentPort, LoadTeacherPort {
    override fun saveTeacher(dto: SaveTeacherDto) {
        teacherRepository.save(
            dto.toTeacher()
        )
    }

    override fun saveStudent(dto: SaveStudentDto) {
        studentRepository.save(
            dto.toStudent()
        )
    }

    override fun saveContactor(dto: SaveContactorDto) {
        contactorRepository.save(dto.toContactor())
    }

    override fun loadUser(userEmail: String): User {
        return userRepository.findByEmail(userEmail).orElse(null)
            ?: throw BusinessException(
                "User Not Found -> ${userEmail}",
                ErrorCode.PERSISTENCE_DATA_NOT_FOUND_ERROR
            )
    }

    override fun loadContactor(companyEmail: String): Contactor? {
        return contactorRepository.findFirstByCompanyNumber(companyEmail).orElse(null)
    }

    override fun loadStudent(studentEmail: String): Student? {
        return studentRepository.findByEmail(studentEmail).orElse(null)
    }

    override fun loadStudentListByGenerationAndClassNum(grade: Int, classNum: Int?): List<Student> {
        classNum?.let {
            return studentRepository.findByStudentKeyStartsWith("$grade$it")
        }?: return studentRepository.findByStudentKeyStartsWith("$grade")
    }

    override fun load(userEmail: String): Teacher? {
        return teacherRepository.findByEmail(userEmail).orElse(null)
    }


}
