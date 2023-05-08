package com.info.info_v2_backend.file.application.port.output.user

import com.info.info_v2_backend.common.file.dto.response.UserFileResponse
import com.info.info_v2_backend.file.domain.user.UserProfilePhoto

interface LoadUserFilePort {

    fun load(userEmail: String): UserProfilePhoto?
}
