package com.info.info_v2_backend.user.adapter.output.persistence.repository

import com.info.info_v2_backend.user.domain.Teacher
import org.springframework.data.jpa.repository.JpaRepository

interface TeacherRepository: JpaRepository<Teacher, String> {
}