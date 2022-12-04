package com.info.info_v2_backend.auth.application.port.output

import com.info.info_v2_backend.user.adapter.input.web.rest.dto.response.CommonUserDetails

interface LoadUserDetailsPort {

    fun loadUserDetailsByUsername(email: String): CommonUserDetails?
}