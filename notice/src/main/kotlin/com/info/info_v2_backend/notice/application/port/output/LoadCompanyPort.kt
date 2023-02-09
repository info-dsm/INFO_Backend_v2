package com.info.info_v2_backend.notice.application.port.output

import com.info.info_v2_backend.common.company.CompanyDto

interface LoadCompanyPort {

    fun loadCompany(companyNumber: String): CompanyDto?
    fun loadCompanyThumbnailList(companyNumber: String): MutableList<String>
}