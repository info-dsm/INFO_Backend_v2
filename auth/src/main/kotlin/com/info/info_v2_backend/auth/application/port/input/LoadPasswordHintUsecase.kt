package com.info.info_v2_backend.auth.application.port.input

interface LoadPasswordHintUsecase {

    fun load(companyNumber: String): String
}