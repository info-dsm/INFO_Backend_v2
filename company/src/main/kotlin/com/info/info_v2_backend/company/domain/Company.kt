package com.info.info_v2_backend.company.domain

import com.info.info_v2_backend.common.company.CompanyDto
import com.info.info_v2_backend.company.adapter.input.web.rest.dto.request.edit.EditCompanyRequest
import com.info.info_v2_backend.company.adapter.input.web.rest.dto.response.CompanyIntroductionResponse
import com.info.info_v2_backend.company.adapter.input.web.rest.dto.response.MaximumCompanyResponse
import com.info.info_v2_backend.company.adapter.input.web.rest.dto.response.MinimumCompanyResponse
import com.info.info_v2_backend.company.domain.businessArea.BusinessAreaTagged
import com.info.info_v2_backend.company.domain.information.CompanyInformation
import com.info.info_v2_backend.company.domain.introduction.CompanyIntroduction
import com.info.info_v2_backend.company.domain.name.CompanyName
import com.info.info_v2_backend.company.domain.status.CompanyCreationStatus
import com.info.info_v2_backend.company.domain.time.TimeEntity
import com.info.info_v2_backend.user.adapter.input.web.rest.dto.response.ContactorResponse
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Where
import java.time.LocalDate
import javax.persistence.*


@Entity
@Where(clause = "company_is_delete = false and company_creation_status = 'CREATED'")
@SQLDelete(sql = "UPDATE `company` SET company_is_delete = true where company_number = ?")
@Table(name = "company")
class Company(
    companyNumber: String,
    companyName: CompanyName,
    companyInformation: CompanyInformation,
    contactor: ContactorId,
    companyIntroduction: CompanyIntroduction
): TimeEntity() {
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
    var isLeading: Boolean = false
        protected set

    @ElementCollection
    var isNoticeRegisteredYearList: MutableList<Int> = ArrayList()
        protected set


    @Column(name = "company_is_associated", nullable = false)
    var isAssociated: Boolean = false
        protected set

    @Column(name = "company_is_delete")
    var isDeleted: Boolean = false
        protected set

    @Column(name = "company_creation_status", nullable = false)
    @Enumerated(value = EnumType.STRING)
    var creationStatus: CompanyCreationStatus = CompanyCreationStatus.WAITING
        protected set

    fun created() {
        this.creationStatus = CompanyCreationStatus.CREATED
    }

    fun updateLastNoticeYear() {
        if (!this.isNoticeRegisteredYearList.contains(
                LocalDate.now().year
            )) this.isNoticeRegisteredYearList.add(LocalDate.now().year)
    }

    fun editCompany(request: EditCompanyRequest) {
        request.companyName?.let {
            this.companyName = CompanyName(it)
        }
        request.companyInformationRequest?.let {
            this.companyInformation.changeCompanyInformation(it)
        }
        request.introduction?.let {
            this.companyIntroduction.changeIntroduction(it)
        }

    }

    fun makeAssociated() {
        this.isAssociated = true
    }

    fun makeLeading() {
        this.isLeading = true
    }

    fun toCompanyDto(): CompanyDto {
        return CompanyDto(
            this.companyNumber,
            this.companyName.companyName,
            this.companyInformation.companyPhone,
            this.companyContact.contactorEmail,
            this.companyIntroduction.introduction,
            this.isLeading
        )
    }
    fun toMinimumCompanyResponse(
        companyIntroductionResponse: CompanyIntroductionResponse,
        totalHiredStudentCount: Int
    ): MinimumCompanyResponse {
        return MinimumCompanyResponse(
            this.companyNumber,
            this.companyContact.contactorEmail,
            this.companyName.companyName,
            this.companyInformation.homeAddress,
            this.businessAreaTaggedList.map {
                it.businessArea
            },
            this.companyInformation.workerCount,
            this.companyInformation.annualSales,
            this.isLeading,
            this.isAssociated,
            this.isNoticeRegisteredYearList.maxOrNull(),
            totalHiredStudentCount,
            companyIntroductionResponse
        )
    }

    fun toMaximumCompanyResponse(companyContactor: ContactorResponse, companyIntroductionResponse: CompanyIntroductionResponse): MaximumCompanyResponse {
        return MaximumCompanyResponse(
            this.companyNumber,
            this.companyName.companyName,
            companyContactor,
            this.companyInformation.toCompanyInformationRequest(),
            this.businessAreaTaggedList.map {
                it.businessArea
            },
            this.isLeading,
            this.isAssociated,
            this.isNoticeRegisteredYearList.maxOrNull(),
            companyIntroductionResponse
        )
    }
}