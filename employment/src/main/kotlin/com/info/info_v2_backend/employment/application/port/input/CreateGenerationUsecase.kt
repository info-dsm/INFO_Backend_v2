package com.info.info_v2_backend.employment.application.port.input

import com.info.info_v2_backend.employment.adapter.input.rest.dto.request.CreateGenerationGradeRequest

interface CreateGenerationUsecase {

    fun createGenerationClass(request: CreateGenerationGradeRequest.CreateGenerationClassRequest)
    fun createGenerationGrade(request: CreateGenerationGradeRequest)
}
