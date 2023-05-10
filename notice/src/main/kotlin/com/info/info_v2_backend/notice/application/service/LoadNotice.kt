package com.info.info_v2_backend.notice.application.service

import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.common.exception.ErrorCode
import com.info.info_v2_backend.common.notice.NoticeDto
import com.info.info_v2_backend.notice.adapter.input.rest.dto.response.AdminMaximumNoticeResponse
import com.info.info_v2_backend.notice.adapter.input.rest.dto.response.MaximumNoticeResponse
import com.info.info_v2_backend.notice.adapter.input.rest.dto.response.MinimumNoticeResponse
import com.info.info_v2_backend.notice.adapter.input.rest.dto.response.MinimumNoticeWithApproveStatusResponse
import com.info.info_v2_backend.notice.application.port.input.CountOpenNoticeUsecase
import com.info.info_v2_backend.notice.application.port.input.LoadNoticeUsecase
import com.info.info_v2_backend.notice.application.port.output.LoadCompanyPort
import com.info.info_v2_backend.notice.application.port.output.LoadNoticePort
import com.info.info_v2_backend.notice.application.port.output.LoadWithConditionPort
import com.info.info_v2_backend.notice.application.port.output.file.FilePort
import com.info.info_v2_backend.notice.application.port.output.noticePreference.LoadNoticePreferencePort
import com.info.info_v2_backend.notice.domain.status.NoticeWaitingStatus
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class LoadNotice(
    private val loadNoticePort: LoadNoticePort,
    private val loadWithConditionPort: LoadWithConditionPort,
    private val filePort: FilePort,
    private val loadCompanyPort: LoadCompanyPort,
    private val loadNoticePreferencePort: LoadNoticePreferencePort
): LoadNoticeUsecase, CountOpenNoticeUsecase {

    private val log = LoggerFactory.getLogger(this.javaClass)

    override fun loadMaximumNotice(noticeId: String): MaximumNoticeResponse {
        val maximumNoticeResponse = (loadNoticePort.loadNotice(noticeId)
            ?: throw BusinessException(
                "Notice를 조회하지 못했습니다.",
                ErrorCode.PERSISTENCE_DATA_NOT_FOUND_ERROR)
                ).toMaximumNoticeResponse()
        maximumNoticeResponse.addAllAttachmentFileList(filePort.loadAttachmentList(noticeId).toMutableList())
        return maximumNoticeResponse
    }

    override fun loadAdminMaximumNotice(noticeId: String): AdminMaximumNoticeResponse {
        val maximumNoticeResponse = (loadNoticePort.loadNotice(noticeId)
            ?: throw BusinessException(
                "Notice를 조회하지 못했습니다.",
                ErrorCode.PERSISTENCE_DATA_NOT_FOUND_ERROR)
                ).toAdminMaximumNoticeResponse()
        maximumNoticeResponse.addAllAttachmentFileList(filePort.loadAttachmentList(noticeId).toMutableList())
        return maximumNoticeResponse
    }

    override fun loadNotEndedMinimumNoticeList(idx: Int, size: Int): Page<MinimumNoticeResponse> {
        return loadWithConditionPort.loadBeforeEndDateAndStatusNoticeList(idx, size, LocalDate.now(), NoticeWaitingStatus.APPROVE)
    }

    override fun loadEndedMinimumNoticeList(idx: Int, size: Int): Page<MinimumNoticeResponse> {
        return loadWithConditionPort.loadAfterEndDateAndStatusNoticeList(idx, size, LocalDate.now(), NoticeWaitingStatus.APPROVE)
    }

    override fun loadCompanyMinimumNoticeList(companyNumber: String): List<MinimumNoticeResponse> {
        return loadNoticePort.loadNoticeListByCompanyNumber(companyNumber).filter {
            it.approveStatus == NoticeWaitingStatus.APPROVE
        }.map {
            it.toMinimumNoticeResponse(
                loadCompanyPort.loadCompanyThumbnailList(companyNumber)
            )
        }
    }

    override fun loadCompanyMinimumNoticeWithApproveStatusList(companyNumber: String): List<MinimumNoticeWithApproveStatusResponse> {
        return loadNoticePort.loadNoticeListByCompanyNumber(companyNumber).map {
            it.toMinimumNoticeWithApproveStatusResponse(
                loadCompanyPort.loadCompanyThumbnailList(companyNumber)
            )
        }
    }

    override fun loadCustomNoticeList(userEmail: String, idx: Int, size: Int): Page<MinimumNoticeResponse> {
        loadNoticePreferencePort.loadNoticePreference(
            userEmail
        )?.let {
            return loadNoticePort.loadNoticePageBySmallClassification(
                it.smallClassification.name, idx, size
            ).map {
                it.toMinimumNoticeResponse(
                    loadCompanyPort.loadCompanyThumbnailList(it.company.companyNumber)
                )
            }
        }?: return loadWithConditionPort.loadBeforeEndDateAndStatusNoticeList(idx, size, LocalDate.now(), NoticeWaitingStatus.APPROVE)
    }


    override fun loadAvailableNotice(noticeId: String): NoticeDto? {
        val notice = loadNoticePort.loadNotice(noticeId)
        notice?.let {
            if (it.checkIsAvailableAppliesStatus())
                return it.toNoticeDto()
            log.info("notice is not available: $noticeId")
            return null
        }
        log.info("System called notice, but cannot find notice: $noticeId")
        return null
    }

    override fun loadNoticeDto(noticeId: String): NoticeDto? {
        return loadNoticePort.loadNotice(noticeId)?.toNoticeDto()
    }

    override fun loadAvailableNoticeByCompanyNumber(companyNumber: String): List<NoticeDto> {
        return loadNoticePort.loadNoticeListByCompanyNumber(companyNumber).map {
            it.toNoticeDto()
        }
    }

    override fun searchNotice(
        companyName: String?,
        smallClassification: String?,
        idx: Int,
        size: Int
    ): Page<MinimumNoticeResponse> {
        companyName?.let {
            name: String ->
            smallClassification?.let {
                classification: String ->
                return loadNoticePort.loadNoticePageByCompanyNameAndSmallClassification(name, classification, idx, size).map {
                    it.toMinimumNoticeResponse(
                        loadCompanyPort.loadCompanyThumbnailList(it.company.companyNumber)
                    )
                }
            }?:let {
                return loadNoticePort.loadNoticePageByCompanyName(name, idx, size).map {
                    it.toMinimumNoticeResponse(
                        loadCompanyPort.loadCompanyThumbnailList(it.company.companyNumber)
                    )
                }
            }
        }?:let {
            smallClassification?.let {
                return loadNoticePort.loadNoticePageBySmallClassification(smallClassification, idx, size).map {
                    it.toMinimumNoticeResponse(
                        loadCompanyPort.loadCompanyThumbnailList(it.company.companyNumber)
                    )
                }
            }?: return loadWithConditionPort.loadBeforeEndDateAndStatusNoticeList(idx, size, LocalDate.now(), NoticeWaitingStatus.APPROVE)
        }
    }

    override fun count(): Int {
        return loadNoticePort.countOpenNotice()
    }


}
