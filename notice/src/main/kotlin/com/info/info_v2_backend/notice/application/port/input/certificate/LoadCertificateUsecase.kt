package com.info.info_v2_backend.notice.application.port.input.certificate

import com.info.info_v2_backend.notice.adapter.input.rest.dto.response.certificate.CertificateResponse

interface LoadCertificateUsecase {

    fun loadAll(): List<CertificateResponse>
}