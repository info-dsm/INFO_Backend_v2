package com.info.info_v2_backend.user.adapter.output.persistence.repository

import com.info.info_v2_backend.user.domain.Student
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface StudentRepository: JpaRepository<Student, String> {

    fun findByEmail(email: String): Optional<Student>
    fun findByEntranceYear(entranceYear: Int): List<Student>
}