package com.info.info_v2_backend.notice.adapter.output.persistence.jpa.certificate

import com.info.info_v2_backend.notice.adapter.output.persistence.jpa.repository.CertificateUsageRepository
import com.info.info_v2_backend.notice.application.port.output.certificate.RemoveCertificateUsagePort
import com.info.info_v2_backend.notice.application.port.output.certificate.SaveCertificateUsagePort
import com.info.info_v2_backend.notice.domain.certificate.CertificateUsage
import org.springframework.stereotype.Service

@Service
class CertificateUsageAdapter(
    private val certificateUsageRepository: CertificateUsageRepository
): SaveCertificateUsagePort, RemoveCertificateUsagePort {
    override fun save(certificateUsage: CertificateUsage) {
        certificateUsageRepository.save(certificateUsage)
    }

    override fun removeByNotice(noticeId: String) {
        certificateUsageRepository.deleteByNotice(noticeId)
    }



}