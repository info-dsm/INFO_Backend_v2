package com.info.info_v2_backend.notice.domain.language

import com.info.info_v2_backend.notice.domain.Notice
import com.info.info_v2_backend.notice.domain.time.TimeEntity
import org.springframework.data.domain.Persistable
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.IdClass
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "language_usage")
@IdClass(LanguageUsageIdClass::class)
class LanguageUsage(
    language: Language,
    notice: Notice
): TimeEntity(), Persistable<String>, java.io.Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "language_id", nullable = false)
    val language: Language = language

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