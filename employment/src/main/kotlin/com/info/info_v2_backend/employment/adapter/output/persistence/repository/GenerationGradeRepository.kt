package com.info.info_v2_backend.employment.adapter.output.persistence.repository

import com.info.info_v2_backend.employment.domain.generation.GenerationGrade
import org.springframework.data.jpa.repository.JpaRepository

interface GenerationGradeRepository: JpaRepository<GenerationGrade, Int> {
}
