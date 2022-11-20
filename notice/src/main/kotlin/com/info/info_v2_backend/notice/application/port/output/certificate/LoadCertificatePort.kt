package com.info.info_v2_backend.notice.application.port.output.certificate

import com.info.info_v2_backend.notice.domain.certificate.Certificate

interface LoadCertificatePort {

    fun load(certificateName: String): Certificate?
}