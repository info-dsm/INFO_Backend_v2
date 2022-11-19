package com.info.info_v2_backend.user.application.port.input

interface LoadPasswordHintUsecase {

    fun load(email: String): String?
}