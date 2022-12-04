package com.info.info_v2_backend.notice.application.port.input.classification

import com.info.info_v2_backend.notice.adapter.input.rest.dto.request.classification.AddClassificationRequest

interface AddClassificationUsecase {

    fun addClassification(request: AddClassificationRequest)
}