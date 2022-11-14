package com.info.info_v2_backend.email.domain.user

import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class Sender(
    senderEmail: String?,
) {
    @Column(name = "sender_email", nullable = false)
    val senderEmail: String? = senderEmail

}