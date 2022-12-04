package com.info.info_v2_backend.notice.adapter.output.persistence.jpa.certificate

import com.info.info_v2_backend.notice.adapter.output.persistence.jpa.repository.CertificateRepository
import com.info.info_v2_backend.notice.application.port.output.certificate.LoadCertificatePort
import com.info.info_v2_backend.notice.domain.certificate.Certificate
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class CertificateAdapter(
    private val certificateRepository: CertificateRepository
): LoadCertificatePort {
    override fun load(certificateName: String): Certificate? {
        return certificateRepository.findByIdOrNull(certificateName)
    }

    override fun loadAll(): List<Certificate> {
        return certificateRepository.findAll()
    }


}