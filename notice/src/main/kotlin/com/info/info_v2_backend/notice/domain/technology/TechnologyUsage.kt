package com.info.info_v2_backend.notice.domain.technology

import com.info.info_v2_backend.commonEntity.entity.TimeEntity
import com.info.info_v2_backend.notice.domain.Notice
import org.springframework.data.domain.Persistable
import java.io.Serializable
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.IdClass
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table


@Entity
@IdClass(TechnologyUsageIdClass::class)
@Table(name = "technology_usage")
class TechnologyUsage(
    technology: Technology,
    notice: Notice
): TimeEntity(), Persistable<String>, Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "technology_id", nullable = false)
    val technology: Technology = technology

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