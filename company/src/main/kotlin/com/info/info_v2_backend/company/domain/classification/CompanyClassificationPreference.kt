package com.info.info_v2_backend.company.domain.classification

import javax.persistence.*

@Entity
@IdClass(CompanyClassificationPreferenceIdClass::class)
class CompanyClassificationPreference(
    companyClassification: CompanyClassification,
    userEmail: String
) {

    @Id
    @Enumerated(value = EnumType.STRING)
    var companyClassification: CompanyClassification = companyClassification

    @Id
    var userEmail: String = userEmail

}
