package com.info.info_v2_backend.notice.domain.certificate

import com.info.info_v2_backend.notice.domain.Notice
import com.info.info_v2_backend.notice.domain.time.TimeEntity
import org.springframework.data.domain.Persistable
import java.io.Serializable
import javax.persistence.*


@Entity
@IdClass(CertificateUsageIdClass::class)
@Table(name = "certificate_usage")
class CertificateUsage(
    certificate: Certificate,
    notice: Notice
): TimeEntity(), Persistable<String>, Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "certificate_id", nullable = false)
    val certificate: Certificate = certificate

    @Id
    @ManyToOne
    @JoinColumn(name = "notice_id", nullable = false)
    val notice: Notice = notice


    override fun getId(): String? {
        return this.id
    }

    override fun isNew(): Boolean {
        return this.createdAt == null
    }


}