package com.info.info_v2_backend.email.application.port.output

import com.info.info_v2_backend.common.user.UserEmailIdDto

interface LoadEmailUserPort {

    fun loadEmailUser(userEmail: String): UserEmailIdDto
}