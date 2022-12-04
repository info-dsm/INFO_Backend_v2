package com.info.info_v2_backend.notice.application.service.certificate

import com.info.info_v2_backend.notice.adapter.input.rest.dto.response.certificate.CertificateResponse
import com.info.info_v2_backend.notice.application.port.input.certificate.LoadCertificateUsecase
import com.info.info_v2_backend.notice.application.port.output.certificate.LoadCertificatePort
import org.springframework.stereotype.Service

@Service
class LoadCertificate(
    private val loadCertificatePort: LoadCertificatePort
): LoadCertificateUsecase {

    override fun loadAll(): List<CertificateResponse> {
        return loadCertificatePort.loadAll().map {
            it.toCertificateResponse()
        }
    }


}