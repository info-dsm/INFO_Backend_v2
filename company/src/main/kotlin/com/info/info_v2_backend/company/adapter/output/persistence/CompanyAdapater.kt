package com.info.info_v2_backend.company.adapter.output.persistence

import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.common.exception.ErrorCode
import com.info.info_v2_backend.company.adapter.output.persistence.repository.CompanyRepository
import com.info.info_v2_backend.company.application.port.output.company.LoadCompanyPort
import com.info.info_v2_backend.company.application.port.output.company.SaveCompanyPort
import com.info.info_v2_backend.company.domain.Company
import org.hibernate.JDBCException
import org.hibernate.exception.ConstraintViolationException
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Service
class CompanyAdapater(
    private val companyRepository: CompanyRepository
): SaveCompanyPort, LoadCompanyPort {

    @Transactional(propagation = Propagation.REQUIRES_NEW)
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

    override fun loadCompany(companyId: String): Company?{
        return companyRepository.findById(companyId).orElse(null)
    }

    override fun loadAllCompanyList(idx: Int, size: Int): Page<Company> {
        return companyRepository.findAll(PageRequest.of(idx, size, Sort.by("created_at").descending()))
    }

    override fun loadAllCompanyListByYear(idx: Int, size: Int, year: Int): Page<Company> {
        return companyRepository.findByIsNoticeRegisteredYearListContaining(year, PageRequest.of(idx, size, Sort.by("created_at").descending()))
    }

}