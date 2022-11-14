package com.info.info_v2_backend.company.adapter.output.persistence

import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.common.exception.ErrorCode
import com.info.info_v2_backend.company.adapter.output.persistence.repository.CompanyRepository
import com.info.info_v2_backend.company.application.port.output.LoadCompanyPort
import com.info.info_v2_backend.company.application.port.output.SaveCompanyPort
import com.info.info_v2_backend.company.domain.Company
import org.springframework.stereotype.Service

@Service
class CompanyAdapater(
    private val companyRepository: CompanyRepository
): SaveCompanyPort, LoadCompanyPort {

    override fun save(company: Company) {
        companyRepository.save(company)
    }

    override fun loadCompany(companyId: String): Company {
        return companyRepository.findById(companyId).orElse(null)
            ?: throw BusinessException("company not found -> ${companyId}", ErrorCode.PERSISTENCE_DATA_NOT_FOUND_ERROR)
    }

}