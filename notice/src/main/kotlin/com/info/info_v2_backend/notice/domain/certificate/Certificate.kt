package com.info.info_v2_backend.notice.domain.certificate

import com.info.info_v2_backend.notice.adapter.input.rest.dto.response.certificate.CertificateResponse
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.Table


@Entity
@Table(name = "certificate")
class Certificate(
    name: String
) {

    @Id
    @Column(name = "certificate_name", nullable = false)
    val name: String = name

    @OneToMany(mappedBy = "certificate")
    val certificateUsageList: MutableList<CertificateUsage> = ArrayList()

    fun toCertificateResponse(): CertificateResponse {
        return CertificateResponse(
            this.name
        )
    }
}