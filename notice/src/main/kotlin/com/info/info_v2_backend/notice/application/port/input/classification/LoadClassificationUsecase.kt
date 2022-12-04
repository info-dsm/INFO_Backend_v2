package com.info.info_v2_backend.notice.application.port.input.classification

import com.info.info_v2_backend.notice.adapter.input.rest.dto.response.classification.ClassificationResponse

interface LoadClassificationUsecase {

    fun load(): List<ClassificationResponse>
}