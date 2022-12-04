package com.info.info_v2_backend.company.adapter.output.persistence.rdb.repository

import com.info.info_v2_backend.company.domain.businessArea.BusinessArea
import org.springframework.data.jpa.repository.JpaRepository

interface BusinessAreaRepository: JpaRepository<BusinessArea, String> {
}