package com.info.info_v2_backend.employment.adapter.input.rest.dto.request

data class CreateGenerationClassRequest(
    val generation: Int,
    val classNum: Int,
    val totalClassStudent: Int,
    val major: String,
    val description: String
)
