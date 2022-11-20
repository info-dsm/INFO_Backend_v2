package com.info.info_v2_backend.notice.application.port.output.certificate

import com.info.info_v2_backend.notice.domain.certificate.CertificateUsage

interface RemoveCertificateUsagePort {

    fun removeByNotice(noticeId: String)
}