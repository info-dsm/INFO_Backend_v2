package com.info.info_v2_backend.notice.application.service

import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.common.exception.ErrorCode
import com.info.info_v2_backend.common.file.dto.request.GenerateFileListRequest
import com.info.info_v2_backend.common.file.dto.response.PresignedUrlListResponse
import com.info.info_v2_backend.notice.adapter.input.rest.dto.request.CreateNoticeRequest
import com.info.info_v2_backend.notice.application.port.input.CreateNoticeUsecase
import com.info.info_v2_backend.notice.application.port.output.LoadCompanyPort
import com.info.info_v2_backend.notice.application.port.output.SaveNoticePort
import com.info.info_v2_backend.notice.application.port.output.UpdateCompanyPort
import com.info.info_v2_backend.notice.application.port.output.certificate.LoadCertificatePort
import com.info.info_v2_backend.notice.application.port.output.certificate.SaveCertificateUsagePort
import com.info.info_v2_backend.notice.application.port.output.file.FilePort
import com.info.info_v2_backend.notice.application.port.output.language.LoadLanguagePort
import com.info.info_v2_backend.notice.application.port.output.language.SaveLanguageUsagePort
import com.info.info_v2_backend.notice.application.port.output.smallClassification.LoadSmallClassificationPort
import com.info.info_v2_backend.notice.application.port.output.smallClassification.SaveSmallClassificationUsagePort
import com.info.info_v2_backend.notice.application.port.output.technology.LoadTechnologyPort
import com.info.info_v2_backend.notice.application.port.output.technology.SaveTechnologyUsagePort
import com.info.info_v2_backend.notice.domain.Notice
import com.info.info_v2_backend.notice.domain.certificate.CertificateUsage
import com.info.info_v2_backend.notice.domain.company.NoticeCompany
import com.info.info_v2_backend.notice.domain.language.LanguageUsage
import com.info.info_v2_backend.notice.domain.recruitmentBusiness.RecruitmentSmallClassificationUsage
import com.info.info_v2_backend.notice.domain.technology.TechnologyUsage
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class CreateNotice(
    private val saveNoticePort: SaveNoticePort,
    private val loadCompanyPort: LoadCompanyPort,
    private val loadSmallClassificationPort: LoadSmallClassificationPort,
    private val updateCompanyPort: UpdateCompanyPort,
    private val filePort: FilePort,
    private val saveSmallClassificationUsagePort: SaveSmallClassificationUsagePort,
    private val loadLanguagePort: LoadLanguagePort,
    private val saveLanguageUsagePort: SaveLanguageUsagePort,
    private val loadTechnologyPort: LoadTechnologyPort,
    private val saveTechnologyUsagePort: SaveTechnologyUsagePort,
    private val loadCertificatePort: LoadCertificatePort,
    private val saveCertificateUsagePort: SaveCertificateUsagePort,

    ): CreateNoticeUsecase {

    override fun create(companyNumber: String, request: CreateNoticeRequest): PresignedUrlListResponse {
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

        request.needCertificateList.map {
                name: String ->
            val certificate = loadCertificatePort.load(name)
                ?: return@map
            saveCertificateUsagePort.save(
                CertificateUsage(
                    certificate,
                    notice
                )
            )

        }

        request.technologyList.map {
                name: String ->
            val technology = loadTechnologyPort.load(name)
                ?: return@map
            saveTechnologyUsagePort.save(
                TechnologyUsage(
                    technology,
                    notice
                )
            )
        }

        request.languageList.map {
                name: String ->
            val language = loadLanguagePort.load(name)
                ?: return@map
            saveLanguageUsagePort.save(
                LanguageUsage(
                    language,
                    notice
                )
            )
        }
        return filePort.saveFile(
            notice.id,
            GenerateFileListRequest(request.generateFileListRequest)
        )
    }
}