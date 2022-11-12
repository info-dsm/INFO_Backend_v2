package com.info.info_v2_backend.commonEntity.entity

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.*

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class TimeEntity {

    @CreatedDate
    @Column(name = "created_at")
    open var createdAt: LocalDateTime? = null
        protected set

    @LastModifiedDate
    @Column(name = "update_at")
    open var updatedAt: LocalDateTime? = null
        protected set


}