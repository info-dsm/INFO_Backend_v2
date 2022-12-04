package com.info.info_v2_backend.notice.application.service.classification

import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.common.exception.ErrorCode
import com.info.info_v2_backend.notice.adapter.input.rest.dto.request.classification.AddClassificationRequest
import com.info.info_v2_backend.notice.application.port.input.classification.AddClassificationUsecase
import com.info.info_v2_backend.notice.application.port.output.bigClassification.LoadBigClassificationPort
import com.info.info_v2_backend.notice.application.port.output.bigClassification.SaveBigClassificationPort
import com.info.info_v2_backend.notice.application.port.output.smallClassification.LoadSmallClassificationPort
import com.info.info_v2_backend.notice.application.port.output.smallClassification.SaveSmallClassificationPort
import com.info.info_v2_backend.notice.domain.recruitmentBusiness.RecruitmentBigClassification
import com.info.info_v2_backend.notice.domain.recruitmentBusiness.RecruitmentSmallClassification
import org.springframework.stereotype.Service

@Service
class AddClassification(
    private val loadSmallClassificationPort: LoadSmallClassificationPort,
    private val saveSmallClassificationPort: SaveSmallClassificationPort,
    private val loadBigClassificationPort: LoadBigClassificationPort,
    private val saveBigClassificationPort: SaveBigClassificationPort
): AddClassificationUsecase {
    override fun addClassification(request: AddClassificationRequest) {
        request.smallClassification?.let {
            loadSmallClassificationPort.loadSmallClassification(it)
                ?.let {
                    throw BusinessException(
                        "이미 존재하는 데이터 입니다. -> smallClassification: ${it.name}" +
                                "bigClassification: ${it.bigClassification.name}",
                        ErrorCode.INVALID_INPUT_DATA_ERROR
                    )
                }
        }
        val bigClassification = loadBigClassificationPort.loadBigClassification(
            request.bigClassification
        )?: saveBigClassificationPort.saveBigClassification(
            RecruitmentBigClassification(
                request.bigClassification
            )
        )

        request.smallClassification?.let {
            saveSmallClassificationPort.saveSmallClassification(
                RecruitmentSmallClassification(
                    it,
                    bigClassification
                )
            )
        }
    }
}