package com.info.info_v2_backend.applies.domain.time

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class TimeEntity {

    @CreatedDate
    @Column(name = "created_at", updatable = false, nullable = false)
    open var createdAt: LocalDateTime? = null
        protected set

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    open var updatedAt: LocalDateTime? = null
        protected set


}