package com.info.info_v2_backend.user.adapter.output.persistence.repository

import com.info.info_v2_backend.user.domain.Contactor
import org.springframework.data.jpa.repository.JpaRepository

interface ContactorRepository: JpaRepository<Contactor, String> {
}