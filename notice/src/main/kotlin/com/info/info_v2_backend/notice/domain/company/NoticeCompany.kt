package com.info.info_v2_backend.notice.domain.company

import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class NoticeCompany(
    companyNumber: String
) {
    @Column(name = "company_number")
    val companyNumber = companyNumber
}