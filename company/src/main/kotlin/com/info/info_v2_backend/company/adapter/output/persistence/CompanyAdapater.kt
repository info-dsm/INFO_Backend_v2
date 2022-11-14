package com.info.info_v2_backend.company.adapter.output.persistence

import com.info.info_v2_backend.company.adapter.output.persistence.repository.CompanyRepository
import com.info.info_v2_backend.company.application.port.output.SaveCompanyPort
import com.info.info_v2_backend.company.domain.Company
import org.springframework.stereotype.Service

@Service
class CompanyAdapater(
    private val companyRepository: CompanyRepository
): SaveCompanyPort {

    override fun save(company: Company) {
        companyRepository.save(company)
    }

}