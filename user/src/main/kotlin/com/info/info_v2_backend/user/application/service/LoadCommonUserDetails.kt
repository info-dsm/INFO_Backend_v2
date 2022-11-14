package com.info.info_v2_backend.user.application.service

import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.user.adapter.input.web.rest.dto.response.CommonUserDetails
import com.info.info_v2_backend.user.application.port.`in`.LoadCommonUserDetailsUsecase
import com.info.info_v2_backend.user.application.port.out.LoadUserPort
import org.springframework.stereotype.Service

@Service
class LoadCommonUserDetails(
    private val loadUserPort: LoadUserPort
): LoadCommonUserDetailsUsecase {
    override fun load(userEmail: String): CommonUserDetails? {
        try {
            return loadUserPort.load(userEmail).toCommonUserDetails()
        } catch (e: BusinessException) {
            return null
        }
    }
}