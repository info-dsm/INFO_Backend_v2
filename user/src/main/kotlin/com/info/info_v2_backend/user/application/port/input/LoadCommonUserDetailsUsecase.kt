package com.info.info_v2_backend.user.application.port.input

import com.info.info_v2_backend.user.adapter.input.web.rest.dto.response.CommonUserDetails

interface LoadCommonUserDetailsUsecase {

    fun load(userEmail: String): CommonUserDetails?
}