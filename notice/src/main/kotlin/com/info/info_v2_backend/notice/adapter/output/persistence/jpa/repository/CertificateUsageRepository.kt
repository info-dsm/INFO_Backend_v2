package com.info.info_v2_backend.notice.adapter.output.persistence.jpa.repository

import com.info.info_v2_backend.notice.domain.certificate.CertificateUsage
import com.info.info_v2_backend.notice.domain.certificate.CertificateUsageIdClass
import org.springframework.data.jpa.repository.JpaRepository

interface CertificateUsageRepository: JpaRepository<CertificateUsage, CertificateUsageIdClass> {

    fun deleteByNotice(noticeId: String)
}