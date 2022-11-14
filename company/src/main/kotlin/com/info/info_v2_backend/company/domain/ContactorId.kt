package com.info.info_v2_backend.company.domain

import javax.persistence.Embeddable

@Embeddable
class ContactorId(
    contactorEmail: String,
) {
    var contactorEmail: String = contactorEmail
        protected set
}