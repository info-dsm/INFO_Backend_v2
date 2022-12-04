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
import com.info.info_v2_backend.notice.application.port.output.file.FilePort
import com.info.info_v2_backend.notice.application.port.output.smallClassification.LoadSmallClassificationPort
import com.info.info_v2_backend.notice.application.port.output.smallClassification.SaveSmallClassificationPort
import com.info.info_v2_backend.notice.application.port.output.smallClassification.SaveSmallClassificationUsagePort
import com.info.info_v2_backend.notice.domain.Notice
import com.info.info_v2_backend.notice.domain.company.NoticeCompany
import com.info.info_v2_backend.notice.domain.recruitmentBusiness.RecruitmentBigClassification
import com.info.info_v2_backend.notice.domain.recruitmentBusiness.RecruitmentSmallClassification
import com.info.info_v2_backend.notice.domain.recruitmentBusiness.RecruitmentSmallClassificationUsage
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.util.UUID

@Service
class CreateNotice(
    private val saveNoticePort: SaveNoticePort,
    private val loadCompanyPort: LoadCompanyPort,
    private val loadSmallClassificationPort: LoadSmallClassificationPort,
    private val updateCompanyPort: UpdateCompanyPort,
    private val filePort: FilePort,
    private val saveSmallClassificationUsagePort: SaveSmallClassificationUsagePort
): CreateNoticeUsecase {

    override fun create(companyNumber: String, request: CreateNoticeRequest, attachment: List<MultipartFile>) {
        val companyDto = loadCompanyPort.loadCompany(companyNumber)
            ?: throw BusinessException("회사를 조회하지 못했습니다. -> $companyNumber",
            ErrorCode.PERSISTENCE_DATA_NOT_FOUND_ERROR)

        val smallClassification = request.smallClassificationList.map {
            return@map loadSmallClassificationPort.loadSmallClassification(it)
                ?: throw BusinessException("Small Classification를 처리하던 중 오류가 발생했습니다. -> $it", ErrorCode.INVALID_INPUT_DATA_ERROR)
        }

        val notice = Notice(
            UUID.randomUUID().toString(),
            NoticeCompany(companyDto.companyNumber, companyDto.companyName),
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
            request.isPersonalContact,
            request.interviewProcessMap.toMutableMap()
        )
        updateCompanyPort.updateLastNoticedYear(companyNumber)

        filePort.saveFile(notice.id, attachment)
        saveNoticePort.saveNotice(
            notice
        )

        smallClassification.map {
            saveSmallClassificationUsagePort.save(
                RecruitmentSmallClassificationUsage(
                    notice,
                    it
                )
            )
        }

        saveNoticePort.saveNotice(
            notice
        )

    }
}