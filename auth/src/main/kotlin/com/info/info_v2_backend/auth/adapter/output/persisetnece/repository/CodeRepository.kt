package com.info.info_v2_backend.auth.adapter.output.persisetnece.repository

import com.info.info_v2_backend.auth.domain.Code
import com.info.info_v2_backend.common.auth.AuthenticationCodeType
import org.springframework.data.repository.CrudRepository
import java.util.Optional

interface CodeRepository: CrudRepository<Code, String> {

    fun deleteByTargetEmail(email: String)

    fun findByTargetEmailAndType(email: String, type: AuthenticationCodeType): Optional<Code>
}