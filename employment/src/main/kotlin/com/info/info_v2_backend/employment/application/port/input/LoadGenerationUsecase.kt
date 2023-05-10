package com.info.info_v2_backend.employment.application.port.input

import com.info.info_v2_backend.employment.domain.generation.GenerationGrade

interface LoadGenerationUsecase {

    fun load(generation: Int): GenerationGrade
}
