package com.info.info_v2_backend.notice.domain.company

import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class NoticeCompany(
    companyNumber: String,
    companyName: String,
) {
    @Column(name = "company_number")
    val companyNumber = companyNumber

    @Column(name = "company_name")
    val companyName = companyName


    override fun equals(other: Any?): Boolean {
        if (other is NoticeCompany) return this.companyNumber == other.companyNumber
        return super.equals(other)
    }
}