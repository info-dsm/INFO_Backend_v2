package com.info.info_v2_backend.company.adapter.output.persistence.repository

import com.info.info_v2_backend.company.domain.Company
import org.springframework.data.jpa.repository.JpaRepository

interface CompanyRepository: JpaRepository<Company, String> {

}