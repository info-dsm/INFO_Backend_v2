package com.info.info_v2_backend.user.application.port.out

import com.info.info_v2_backend.user.domain.User

interface LoadUserPort {

    fun load(userEmail: String): User
}