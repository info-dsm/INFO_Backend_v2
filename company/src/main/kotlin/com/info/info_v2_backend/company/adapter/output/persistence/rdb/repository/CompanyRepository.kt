package com.info.info_v2_backend.company.adapter.output.persistence.rdb.repository

import com.info.info_v2_backend.company.domain.Company
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.Optional

interface CompanyRepository: JpaRepository<Company, String> {

    @Query("select * from company where company_number = :companyNumber", nativeQuery = true)
    fun findByCompanyNumber(@Param(value = "companyNumber") companyNumber: String): Optional<Company>
    fun findByIsNoticeRegisteredYearListContaining(year: Int, pageable: Pageable): Page<Company>
}