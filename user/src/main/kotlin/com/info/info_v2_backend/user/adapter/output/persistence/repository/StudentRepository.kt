package com.info.info_v2_backend.user.adapter.output.persistence.repository

import com.info.info_v2_backend.user.domain.Student
import org.springframework.data.jpa.repository.JpaRepository

interface StudentRepository: JpaRepository<Student, String> {

}