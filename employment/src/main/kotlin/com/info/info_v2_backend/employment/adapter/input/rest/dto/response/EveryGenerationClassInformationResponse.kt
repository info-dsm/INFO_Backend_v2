package com.info.info_v2_backend.employment.adapter.input.rest.dto.response

import com.info.info_v2_backend.employment.domain.generation.GenerationClass

data class EveryGenerationClassInformationResponse(
    val totalGradeStudent: Int,
    val totalEmployedGradeStudent: Int,
    val classList: List<GenerationClassInformationResponse>
) {

    data class GenerationClassInformationResponse(
        val classNum: Int,
        val information: GenerationClass,
        val totalClassStudent: Int,
        val totalEmployedClassStudent: Int,
    )

}
