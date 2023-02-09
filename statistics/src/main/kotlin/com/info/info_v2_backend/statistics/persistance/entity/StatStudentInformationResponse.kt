package com.info.info_v2_backend.statistics.persistance.entity

import javax.persistence.Embeddable
import javax.persistence.Embedded

@Embeddable
class StatStudentInformationResponse(
    classNum: String,
    company: StatCompanyInformationResponse
) {
    var classNum: String = classNum
        protected set

    @Embedded
    var company: StatCompanyInformationResponse = company
        protected set
}