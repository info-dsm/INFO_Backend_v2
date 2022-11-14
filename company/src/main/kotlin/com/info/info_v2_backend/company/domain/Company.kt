package com.info.info_v2_backend.company.domain

import com.info.info_v2_backend.company.domain.businessArea.BusinessAreaTagged
import com.info.info_v2_backend.company.domain.information.CompanyInformation
import com.info.info_v2_backend.company.domain.introduction.CompanyIntroduction
import com.info.info_v2_backend.company.domain.name.CompanyName
import java.time.LocalDate
import javax.persistence.*


@Entity
class Company(
    companyName: CompanyName,
    companyInformation: CompanyInformation,
    contactor: ContactorId,
    companyIntroduction: CompanyIntroduction,
    isLeading: Boolean,
) {

    @EmbeddedId
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
    var noticeRegisteredYearList: MutableList<Int> = ArrayList()
        protected set


    @Column(name = "company_is_associated", nullable = false)
    var isAssociated: Boolean = false
        protected set


    fun updateLastNoticeYear() {
        if (!this.noticeRegisteredYearList.contains(
                LocalDate.now().year
            )) this.noticeRegisteredYearList.add(LocalDate.now().year)
    }

    fun makeAssociated() {
        this.isAssociated = true
    }
}