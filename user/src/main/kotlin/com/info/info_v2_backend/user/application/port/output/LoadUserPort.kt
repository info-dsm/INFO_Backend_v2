package com.info.info_v2_backend.user.application.port.output

import com.info.info_v2_backend.user.domain.User

interface LoadUserPort {

    fun loadUser(userEmail: String): User
}