package com.info.info_v2_backend.notice.application.service.change

import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.common.exception.ErrorCode
import com.info.info_v2_backend.notice.application.port.input.change.ChangeCertificateUsecase
import com.info.info_v2_backend.notice.application.port.output.LoadNoticePort
import com.info.info_v2_backend.notice.application.port.output.SaveNoticePort
import com.info.info_v2_backend.notice.application.port.output.certificate.LoadCertificatePort
import com.info.info_v2_backend.notice.application.port.output.certificate.RemoveCertificateUsagePort
import com.info.info_v2_backend.notice.application.port.output.certificate.SaveCertificateUsagePort
import com.info.info_v2_backend.notice.domain.certificate.CertificateUsage
import org.springframework.stereotype.Service

@Service
class ChangeCertificate(
    private val loadNoticePort: LoadNoticePort,
    private val removeCertificateUsagePort: RemoveCertificateUsagePort,
    private val loadCertificatePort: LoadCertificatePort,
    private val saveCertificateUsagePort: SaveCertificateUsagePort
): ChangeCertificateUsecase {
    override fun change(noticeId: String, certificateList: List<String>) {
        val notice = loadNoticePort.loadNotice(noticeId)
            ?: throw BusinessException(
                "모집공고를 조회하지 못했습니다.",
                ErrorCode.PERSISTENCE_DATA_NOT_FOUND_ERROR
            )

        removeCertificateUsagePort.removeByNotice(notice.id)
        certificateList.map {
            val certificate = loadCertificatePort.load(it)?: return@map
            saveCertificateUsagePort.save(
                CertificateUsage(certificate, notice)
            )
        }
    }



}