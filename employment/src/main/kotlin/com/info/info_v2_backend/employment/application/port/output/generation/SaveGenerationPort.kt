package com.info.info_v2_backend.employment.application.port.output.generation

import com.info.info_v2_backend.employment.domain.generation.GenerationClass
import com.info.info_v2_backend.employment.domain.generation.GenerationGrade

interface SaveGenerationPort {

    fun saveGenerationClass(generationClass: GenerationClass): GenerationClass
    fun saveGenerationGrade(generationGrade: GenerationGrade): GenerationGrade
}
