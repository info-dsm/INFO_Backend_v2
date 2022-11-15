package com.info.info_v2_backend.company.adapter.output.persistence

import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.common.exception.ErrorCode
import com.info.info_v2_backend.company.adapter.output.persistence.repository.CompanyRepository
import com.info.info_v2_backend.company.application.port.output.company.LoadCompanyPort
import com.info.info_v2_backend.company.application.port.output.company.SaveCompanyPort
import com.info.info_v2_backend.company.domain.Company
import org.hibernate.JDBCException
import org.hibernate.exception.ConstraintViolationException
import org.springframework.stereotype.Service

@Service
class CompanyAdapater(
    private val companyRepository: CompanyRepository
): SaveCompanyPort, LoadCompanyPort {

    override fun save(company: Company) {
        try {
            companyRepository.save(company)
        } catch (e: JDBCException) {
            throw BusinessException(
                "DB작업 중 오류가 발생했습니다. ${e.message}",
                ErrorCode.BAD_GATEWAY_ERROR
            )
        }
    }

    override fun loadCompany(companyId: String): Company {
        return companyRepository.findById(companyId).orElse(null)
            ?: throw BusinessException("company not found -> ${companyId}", ErrorCode.PERSISTENCE_DATA_NOT_FOUND_ERROR)
    }

}