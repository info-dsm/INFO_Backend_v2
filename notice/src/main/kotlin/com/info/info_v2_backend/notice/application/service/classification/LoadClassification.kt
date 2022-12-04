package com.info.info_v2_backend.notice.application.service.classification

import com.info.info_v2_backend.notice.adapter.input.rest.dto.response.classification.ClassificationResponse
import com.info.info_v2_backend.notice.application.port.input.classification.LoadClassificationUsecase
import com.info.info_v2_backend.notice.application.port.output.smallClassification.LoadSmallClassificationPort
import org.springframework.stereotype.Service

@Service
class LoadClassification(
    private val loadSmallClassificationPort: LoadSmallClassificationPort
): LoadClassificationUsecase {
    override fun load(): List<ClassificationResponse> {
        return loadSmallClassificationPort.loadAll().map {
            it.toClassificationResponse()
        }
    }


}