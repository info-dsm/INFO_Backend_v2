package com.info.info_v2_backend.notice.application.service

import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.common.exception.ErrorCode
import com.info.info_v2_backend.notice.adapter.input.rest.dto.request.CreateNoticeRequest
import com.info.info_v2_backend.notice.application.port.input.CreateNoticeUsecase
import com.info.info_v2_backend.notice.application.port.output.LoadCompanyPort
import com.info.info_v2_backend.notice.application.port.output.SaveNoticePort
import com.info.info_v2_backend.notice.application.port.output.UpdateCompanyPort
import com.info.info_v2_backend.notice.application.port.output.bigClassification.LoadBigClassificationPort
import com.info.info_v2_backend.notice.application.port.output.bigClassification.SaveBigClassificationPort
import com.info.info_v2_backend.notice.application.port.output.smallClassification.LoadSmallClassificationPort
import com.info.info_v2_backend.notice.application.port.output.smallClassification.SaveSmallClassificationPort
import com.info.info_v2_backend.notice.domain.Notice
import com.info.info_v2_backend.notice.domain.company.NoticeCompany
import com.info.info_v2_backend.notice.domain.recruitmentBusiness.RecruitmentBigClassification
import com.info.info_v2_backend.notice.domain.recruitmentBusiness.RecruitmentSmallClassification
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class CreateNotice(
    private val saveNoticePort: SaveNoticePort,
    private val loadCompanyPort: LoadCompanyPort,
    private val loadBigClassificationPort: LoadBigClassificationPort,
    private val saveBigClassificationPort: SaveBigClassificationPort,
    private val loadSmallClassificationPort: LoadSmallClassificationPort,
    private val saveSmallClassificationPort: SaveSmallClassificationPort,
    private val updateCompanyPort: UpdateCompanyPort
): CreateNoticeUsecase {

    override fun create(companyNumber: String, request: CreateNoticeRequest) {
        val companyDto = loadCompanyPort.loadCompany(companyNumber)
            ?: throw BusinessException("회사를 조회하지 못했습니다. -> $companyNumber",
            ErrorCode.PERSISTENCE_DATA_NOT_FOUND_ERROR)

        val bigClassification = loadBigClassificationPort.loadBigClassification(
            request.bigClassification
        )?: saveBigClassificationPort.saveBigClassification(
            RecruitmentBigClassification(request.bigClassification)
        )

        val notice = Notice(
            UUID.randomUUID().toString(),
            NoticeCompany(companyDto.companyNumber),
                bigClassification,
            loadSmallClassificationPort.loadSmallClassification(
                request.smallClassification
            )?: saveSmallClassificationPort.saveSmallClassification(
                RecruitmentSmallClassification(
                    request.smallClassification,
                    bigClassification
                )
            ),
            request.detailBusinessDescription,
            request.numberOfEmployee,
            request.gradeCutLine,
            request.workTime.toWorkTime(),
            request.pay.toPay(),
            request.mealSupport.toMealSupport(),
            request.welfare.toWelfare(),
            request.noticeOpenPeriod.toNoticeOpenPeriod(),
            request.needDocuments,
            request.otherFeatures,
            request.workPlace.toWorkPlace(),
            request.isPersonalContact
        )
        updateCompanyPort.updateLastNoticedYear(companyNumber)

        saveNoticePort.saveNotice(
            notice
        )

    }
}