package com.info.info_v2_backend.company.adapter.output.persistence.repository

import com.info.info_v2_backend.company.domain.Company
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface CompanyRepository: JpaRepository<Company, String> {

    fun findByIsNoticeRegisteredYearListContaining(year: Int, pageable: Pageable): Page<Company>
}