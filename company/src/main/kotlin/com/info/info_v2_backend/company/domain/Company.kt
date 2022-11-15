package com.info.info_v2_backend.company.domain

import com.info.info_v2_backend.company.domain.businessArea.BusinessArea
import com.info.info_v2_backend.company.domain.businessArea.BusinessAreaTagged
import com.info.info_v2_backend.company.domain.information.CompanyInformation
import com.info.info_v2_backend.company.domain.introduction.CompanyIntroduction
import com.info.info_v2_backend.company.domain.name.CompanyName
import java.time.LocalDate
import javax.persistence.*


@Entity
class Company(
    companyNumber: String,
    companyName: CompanyName,
    companyInformation: CompanyInformation,
    contactor: ContactorId,
    companyIntroduction: CompanyIntroduction,
    isLeading: Boolean,
) {
    @Id
    @Column(name = "company_number", nullable = false)
    val companyNumber: String = companyNumber

    @Embedded
    var companyName: CompanyName = companyName

    @Embedded
    var companyInformation: CompanyInformation = companyInformation
        protected set

    @Embedded
    var companyContact: ContactorId = contactor


    @OneToMany(mappedBy = "company", cascade = [CascadeType.REMOVE])
    var businessAreaTaggedList: MutableList<BusinessAreaTagged> = ArrayList()
        protected set

    @Embedded
    var companyIntroduction: CompanyIntroduction = companyIntroduction
        protected set

    @Column(name = "company_is_leading", nullable = false)
    var isLeading: Boolean = isLeading
        protected set

    @ElementCollection
    var isNoticeRegisteredYearList: MutableList<Int> = ArrayList()
        protected set


    @Column(name = "company_is_associated", nullable = false)
    var isAssociated: Boolean = false
        protected set

    fun addBusinessAreaTagged(businessAreaTagged: BusinessAreaTagged) {
        this.businessAreaTaggedList.add(businessAreaTagged)
    }

    fun updateLastNoticeYear() {
        if (!this.isNoticeRegisteredYearList.contains(
                LocalDate.now().year
            )) this.isNoticeRegisteredYearList.add(LocalDate.now().year)
    }

    fun makeAssociated() {
        this.isAssociated = true
    }
}