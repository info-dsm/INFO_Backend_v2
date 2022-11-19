package com.info.info_v2_backend.notice.domain.certificate

import com.info.info_v2_backend.commonEntity.entity.TimeEntity
import com.info.info_v2_backend.notice.adapter.input.rest.dto.response.certificate.CertificateResponse
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToMany


@Entity
class Certificate(
    name: String
): TimeEntity() {

    @Id
    @Column(name = "certificate_name", nullable = false)
    val name: String = name

    @OneToMany
    val certificateUsageList: MutableList<CertificateUsage> = ArrayList()

    fun toCertificateResponse(): CertificateResponse {
        return CertificateResponse(
            this.name
        )
    }
}