package com.info.info_v2_backend.company.domain.businessArea

import com.info.info_v2_backend.company.domain.Company
import com.info.info_v2_backend.company.domain.time.TimeEntity
import org.springframework.data.domain.Persistable
import javax.persistence.*



@Entity
@IdClass(BusinessAreaTaggedIdClass::class)
@Table(name = "business_area_tagged")
class BusinessAreaTagged(
    businessArea: BusinessArea,
    company: Company
): TimeEntity(), Persistable<String>, java.io.Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "business_area_id", nullable = false)
    var businessArea: BusinessArea = businessArea

    @Id
    @ManyToOne
    @JoinColumn(name = "company_number", nullable = false)
    var company: Company = company

    override fun getId(): String? {
        return this.id
    }

    override fun isNew(): Boolean {
        return this.createdAt == null
    }


}