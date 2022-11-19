package com.info.info_v2_backend.notice.application.port.output

interface UpdateCompanyPort {

    fun updateLastNoticedYear(companyNumber: String)
}