package com.info.info_v2_backend.auth.adapter.output.persisetnece.repository

import com.info.info_v2_backend.auth.domain.Code
import org.springframework.data.repository.CrudRepository
import java.util.Optional

interface CodeRepository: CrudRepository<Code, String> {

    fun deleteByTargetEmail(email: String)
    fun findByTargetEmail(email: String): Optional<Code>
}