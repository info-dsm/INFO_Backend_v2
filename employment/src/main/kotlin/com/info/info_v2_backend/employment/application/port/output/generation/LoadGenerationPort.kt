package com.info.info_v2_backend.employment.application.port.output.generation

import com.info.info_v2_backend.employment.domain.generation.GenerationClass
import com.info.info_v2_backend.employment.domain.generation.GenerationGrade

interface LoadGenerationPort {

    fun loadClass(classNum: Int, generation: Int): GenerationClass?
    fun loadGrade(generation: Int): GenerationGrade?
}
