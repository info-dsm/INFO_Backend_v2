package com.info.info_v2_backend.company.domain.name

import javax.persistence.Embeddable

@Embeddable
class CompanyName(
    companyName: String,
    companyNumber: String
): java.io.Serializable{
    val companyName = companyName

    val companyNumber = companyNumber

}