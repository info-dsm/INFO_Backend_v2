package com.info.info_v2_backend.notice.domain.certificate

import com.info.info_v2_backend.commonEntity.entity.TimeEntity
import com.info.info_v2_backend.notice.domain.Notice
import org.springframework.data.domain.Persistable
import java.io.Serializable
import javax.persistence.*


@Entity
@Table(name = "ceritifcate_usage")
@IdClass(CertificateUsageIdClass::class)
class CertificateUsage(
    certificate: Certificate,
    notice: Notice
): TimeEntity(), Persistable<String>, Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "certificate_id")
    val certificate: Certificate = certificate

    @Id
    @ManyToOne
    @JoinColumn(name = "notice_id")
    val notice: Notice = notice


    override fun getId(): String? {
        return this.id
    }

    override fun isNew(): Boolean {
        return this.createdAt == null
    }


}