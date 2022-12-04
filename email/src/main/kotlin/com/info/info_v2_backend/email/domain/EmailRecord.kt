package com.info.info_v2_backend.email.domain

import com.info.info_v2_backend.email.domain.content.EmailContent
import com.info.info_v2_backend.email.domain.time.TimeEntity
import com.info.info_v2_backend.email.domain.user.*
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Embedded
import javax.persistence.Entity
import javax.persistence.Id


@Entity
class EmailRecord(
    targetEmail: com.info.info_v2_backend.email.domain.user.Target,
    senderEmail: Sender,
    content: EmailContent,
): TimeEntity() {

    @Id
    @Column(name = "id", nullable = false)
    val id: String = UUID.randomUUID().toString()

    @Embedded
    val targetEmail: com.info.info_v2_backend.email.domain.user.Target = targetEmail

    @Embedded
    val senderEmail: Sender = senderEmail

    @Embedded
    val content: EmailContent = content

    @Column(name = "email_status", nullable = false, length = 1)
    var status: EmailStatus = EmailStatus.SENDING

    fun complete() {
        this.status = EmailStatus.COMPLETE
    }

    fun fail() {
        this.status = EmailStatus.FAILED
    }
}