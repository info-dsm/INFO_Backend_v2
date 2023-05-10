package com.info.info_v2_backend.file.application.port.input.user

import com.info.info_v2_backend.common.file.dto.response.UserFileResponse

interface LoadUserFileUsecase {

    fun load(userEmail: String): UserFileResponse?
}
