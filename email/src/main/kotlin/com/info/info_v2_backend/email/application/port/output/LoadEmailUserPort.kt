package com.info.info_v2_backend.email.application.port.output

interface LoadEmailUserPort {

    fun loadEmailUser(userEmail: String): String?
}