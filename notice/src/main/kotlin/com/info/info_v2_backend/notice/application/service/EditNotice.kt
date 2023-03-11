package com.info.info_v2_backend.notice.application.service

import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.common.exception.ErrorCode
import com.info.info_v2_backend.common.file.dto.request.GenerateFileListRequest
import com.info.info_v2_backend.common.file.dto.response.PresignedUrlListResponse
import com.info.info_v2_backend.notice.adapter.input.rest.dto.request.EditNoticeRequest
import com.info.info_v2_backend.notice.application.port.input.EditNoticeUsecase
import com.info.info_v2_backend.notice.application.port.output.LoadNoticePort
import com.info.info_v2_backend.notice.application.port.output.SaveNoticePort
import com.info.info_v2_backend.notice.application.port.output.certificate.LoadCertificatePort
import com.info.info_v2_backend.notice.application.port.output.certificate.SaveCertificateUsagePort
import com.info.info_v2_backend.notice.application.port.output.file.FilePort
import com.info.info_v2_backend.notice.application.port.output.language.LoadLanguagePort
import com.info.info_v2_backend.notice.application.port.output.language.SaveLanguageUsagePort
import com.info.info_v2_backend.notice.application.port.output.technology.LoadTechnologyPort
import com.info.info_v2_backend.notice.application.port.output.technology.SaveTechnologyUsagePort
import com.info.info_v2_backend.notice.domain.certificate.CertificateUsage
import com.info.info_v2_backend.notice.domain.language.LanguageUsage
import com.info.info_v2_backend.notice.domain.technology.TechnologyUsage
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class EditNotice(
    private val loadNoticePort: LoadNoticePort,
    private val saveNoticePort: SaveNoticePort,
    private val loadLanguagePort: LoadLanguagePort,
    private val saveLanguageUsagePort: SaveLanguageUsagePort,
    private val loadCertificatePort: LoadCertificatePort,
    private val saveCertificateUsagePort: SaveCertificateUsagePort,
    private val loadTechnologyPort: LoadTechnologyPort,
    private val saveTechnologyUsagePort: SaveTechnologyUsagePort,
    private val filePort: FilePort
): EditNoticeUsecase {

    @Transactional
    override fun edit(noticeId: String, request: EditNoticeRequest, companyNumber: String): PresignedUrlListResponse {
        val notice = loadNoticePort.loadNotice(noticeId)
            .takeIf {
                it?.company?.companyNumber == companyNumber
            }
            ?: throw BusinessException(
                "Notice를 찾지 못했습니다. -> $noticeId",
            ErrorCode.PERSISTENCE_DATA_NOT_FOUND_ERROR
            )
        notice.editNotice(request)
        request.interviewProcessMap?.let {
            notice.changeInterviewProcess(it)
        }

        request.certificateList?.let {
            list: MutableList<String> ->
            list.filterNot { str: String ->
                notice.needCertificateUsage.map {
                    it.certificate.name
                }.contains(str)
            }.map {
                saveCertificateUsagePort.save(
                    CertificateUsage(
                        loadCertificatePort.load(it)?:return@map,
                        notice
                    )
                )
            }

        }

        request.technologyList?.let {
            list: MutableList<String> ->
            list.filterNot { str: String ->
                notice.technologyUsage.map {
                    it.technology.name
                }.contains(str)
            }.map {
                saveTechnologyUsagePort.save(
                    TechnologyUsage(
                        loadTechnologyPort.load(it)?:return@map,
                        notice
                    )
                )
            }

        }

        request.languageList?.let {
            list: MutableList<String> ->
            list.filterNot { str: String ->
                notice.languageUsage.map {
                    it.language.name
                }.contains(str)
            }.map {
                saveLanguageUsagePort.save(
                    LanguageUsage(
                        loadLanguagePort.load(it)?:return@map,
                        notice
                    )
                )
            }

        }

        saveNoticePort.saveNotice(notice)
        return filePort.saveFile(
            notice.id,
            GenerateFileListRequest(
                request.generateFileListRequest?:ArrayList()
            )
        )

    }
}