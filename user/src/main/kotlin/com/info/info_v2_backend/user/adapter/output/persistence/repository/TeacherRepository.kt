package com.info.info_v2_backend.user.adapter.output.persistence.repository

import com.info.info_v2_backend.user.domain.Teacher
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface TeacherRepository: JpaRepository<Teacher, String> {

    fun findByEmail(email: String): Optional<Teacher>
}
