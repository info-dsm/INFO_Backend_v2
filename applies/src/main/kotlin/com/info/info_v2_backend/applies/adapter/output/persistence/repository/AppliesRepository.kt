package com.info.info_v2_backend.applies.adapter.output.persistence.repository

import com.info.info_v2_backend.applies.domain.Applies
import com.info.info_v2_backend.common.applies.AppliesStatus
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.Optional

interface AppliesRepository: JpaRepository<Applies, String> {

}