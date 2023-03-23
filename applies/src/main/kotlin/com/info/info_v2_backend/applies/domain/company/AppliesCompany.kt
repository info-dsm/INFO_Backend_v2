package com.info.info_v2_backend.applies.domain.company

import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class AppliesCompany(
    companyNumber: String,
    companyName: String
) {

    @Column(name = "applies_notice_company_number", nullable = false)
    var companyNumber: String = companyNumber
        protected set

    @Column(name = "applies_notice_company_name", nullable = false)
    var companyName: String = companyName
        protected set


}