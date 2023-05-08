package com.info.info_v2_backend.employment.application.port.input

import com.info.info_v2_backend.employment.adapter.input.rest.dto.request.CreateGenerationClassRequest

interface CreateGenerationUsecase {

    fun createGenerationClass(request: CreateGenerationClassRequest)
    fun createGenerationGrade(generation: Int, totalStudent: Int)
}
