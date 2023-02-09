package com.info.info_v2_backend.statistics.persistance.entity

import javax.persistence.Embeddable

@Embeddable
class StatCompanyInformationResponse(
    logo: String,
    name: String
) {
    var logo: String = logo
        protected set
    var name: String = name
        protected set
}