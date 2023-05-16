package com.info.info_v2_backend.user.adapter.output.persistence.repository

import com.info.info_v2_backend.user.domain.Student
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.Optional

interface StudentRepository: JpaRepository<Student, String> {

    fun findByEmail(email: String): Optional<Student>
    @Query(
        nativeQuery = true,
        value = "select * from user a, student b " +
                "where student_key like '%:studentKeyPrefix%' " +
                "and a.email = b.email " +
                "a.user_is_delete = false")
    fun findByStudentKeyStartsWith(@Param("studentKeyPrefix") studentKeyPrefix: String): List<Student>
}
