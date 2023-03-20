package com.info.info_v2_backend.user.adapter.output.persistence.repository

import com.info.info_v2_backend.user.domain.Contactor
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface ContactorRepository: JpaRepository<Contactor, String> {

    fun findFirstByCompanyNumber(companyNumber: String): Optional<Contactor>
}