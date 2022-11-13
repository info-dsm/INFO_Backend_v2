package com.info.info_v2_backend.user.adapter.output.persistence

import com.info.info_v2_backend.user.adapter.input.event.dto.StudentDto
import com.info.info_v2_backend.user.adapter.input.event.dto.TeacherDto
import com.info.info_v2_backend.user.adapter.output.persistence.repository.StudentRepository
import com.info.info_v2_backend.user.adapter.output.persistence.repository.TeacherRepository
import com.info.info_v2_backend.user.application.port.out.SaveUserPersistencePort
import org.springframework.stereotype.Service

@Service
class SaveUserPersistenceAdapter(
    private val teacherRepository: TeacherRepository,
    private val studentRepository: StudentRepository
): SaveUserPersistencePort {
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
}