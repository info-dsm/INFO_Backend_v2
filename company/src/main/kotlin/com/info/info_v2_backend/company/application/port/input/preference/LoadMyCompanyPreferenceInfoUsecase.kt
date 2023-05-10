package com.info.info_v2_backend.company.application.port.input.preference

interface LoadMyCompanyPreferenceInfoUsecase {

    fun load(userEmail: String): String?
}
