package com.info.info_v2_backend.employment.adapter.output.persistence.repository

import com.info.info_v2_backend.employment.domain.generation.GenerationClass
import com.info.info_v2_backend.employment.domain.generation.GenerationClassIdClass
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.Optional

interface GenerationClassRepository: JpaRepository<GenerationClass, GenerationClassIdClass> {

    @Query("select * from generation_class where generation_class = :classNum and generation_grade = :generation", nativeQuery = true)
    fun findByClassNumAndGeneration(@Param("classNum") classNum: Int, @Param("generation") generation: Int): Optional<GenerationClass>
}
