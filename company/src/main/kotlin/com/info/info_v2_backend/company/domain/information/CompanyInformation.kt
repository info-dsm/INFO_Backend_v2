package com.info.info_v2_backend.company.domain.information

import com.info.info_v2_backend.company.adapter.input.web.rest.dto.request.edit.EditCompanyInformationRequest
import com.info.info_v2_backend.company.adapter.input.web.rest.dto.request.register.CompanyInformationRequest
import javax.persistence.AttributeOverride
import javax.persistence.AttributeOverrides
import javax.persistence.Column
import javax.persistence.Embeddable
import javax.persistence.Embedded

@Embeddable
class CompanyInformation(
    homeAddress: AddressInfo,
    agentAddress: AddressInfo?,
    representative: String,
    establishedAt: Int,
    workerCount: Int,
    annualSales: Long,
    companyPhone: String
) {
    @Embedded
    @AttributeOverrides(
        AttributeOverride(name = "fullAddress", column = Column(name = "home_full_address")),
        AttributeOverride(name = "addressNumber", column = Column(name = "home_address_number"))
    )
    var homeAddress: AddressInfo = homeAddress
        protected set

    @Embedded
    @AttributeOverrides(
        AttributeOverride(name = "fullAddress", column = Column(name = "agent_full_address")),
        AttributeOverride(name = "addressNumber", column = Column(name = "agent_address_number"))
    )
    var agentAddress: AddressInfo? = agentAddress
        protected set

    @Column(name = "company_phone_number", nullable = false)
    var companyPhone: String = companyPhone
        protected set

    @Column(name = "representative", nullable = false)
    var representative: String = representative
        protected set

    @Column(name = "established_at", nullable = false)
    var establishedAt: Int = establishedAt
        protected set

    @Column(name = "worker_count", nullable = false)
    var workerCount: Int = workerCount
        protected set

    @Column(name = "annual_sales", nullable = false)
    var annualSales: Long = annualSales
        protected set

    fun changeCompanyInformation(request: EditCompanyInformationRequest) {
        request.homeAddress?.let {
            this.homeAddress = it
        }
        request.agentAddress?.let {
            this.agentAddress = it
        }
        request.representativeName?.let {
            this.representative = it
        }
        request.establishedAt?.let {
            this.establishedAt = it
        }
        request.workerCount?.let {
            this.workerCount = it
        }
        request.annualSales?.let {
            this.annualSales = it
        }
        request.companyPhone?.let {
            this.companyPhone = it
        }

    }

    fun toCompanyInformationRequest(): CompanyInformationRequest {
        return CompanyInformationRequest(
            this.homeAddress,
            this.agentAddress,
            this.representative,
            this.establishedAt,
            this.workerCount,
            this.annualSales,
            this.companyPhone
        )
    }

}