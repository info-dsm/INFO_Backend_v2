package com.info.info_v2_backend.notice.application.service

import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.common.exception.ErrorCode
import com.info.info_v2_backend.notice.adapter.input.rest.dto.request.EditNoticeRequest
import com.info.info_v2_backend.notice.application.port.input.EditNoticeUsecase
import com.info.info_v2_backend.notice.application.port.output.LoadNoticePort
import com.info.info_v2_backend.notice.application.port.output.SaveNoticePort
import com.info.info_v2_backend.notice.application.port.output.certificate.LoadCertificatePort
import com.info.info_v2_backend.notice.application.port.output.certificate.SaveCertificateUsagePort
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
): EditNoticeUsecase {

    @Transactional
    override fun edit(noticeId: String, request: EditNoticeRequest, companyNumber: String) {
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
            list.filter {
                    str: String ->
                !str.equals(
                    notice.needCertificateUsage.map {
                        it.certificate.name
                    }
                )
            }.map {
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

        }

        request.technologyList?.let {
            list: MutableList<String> ->
            list.filter {
                    str: String ->
                !str.equals(
                    notice.technologyUsage.map {
                        it.technology.name
                    }
                )
            }.map {
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

        }

        request.languageList?.let {
            list: MutableList<String> ->
            list.filter {
                    str: String ->
                !str.equals(
                    notice.languageUsage.map {
                        it.language.name
                    }
                )
            }.map {
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
        }
        
        saveNoticePort.saveNotice(notice)
    }
}