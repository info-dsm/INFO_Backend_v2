package com.info.info_v2_backend.statistics.persistance.repository

import com.info.info_v2_backend.statistics.persistance.entity.StatInformation
import org.springframework.data.jpa.repository.JpaRepository

interface StatInformationRepository: JpaRepository<StatInformation, Int> {
}