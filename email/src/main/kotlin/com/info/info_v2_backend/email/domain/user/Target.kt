package com.info.info_v2_backend.email.domain.user

import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class Target(
    targetEmail: String?,
    targetId: String
) {
    @Column(name = "target_email", nullable = false)
    val targetEmail: String? = targetEmail

    @Column(name = "target_id", nullable = false)
    val targetId: String = targetId
}