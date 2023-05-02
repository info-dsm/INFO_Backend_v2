package com.info.info_v2_backend.company.application.port.input

import com.info.info_v2_backend.common.company.CompanyDto
import com.info.info_v2_backend.company.adapter.input.web.rest.dto.response.MaximumCompanyResponse
import com.info.info_v2_backend.company.adapter.input.web.rest.dto.response.MinimumCompanyResponse
import org.springframework.data.domain.Page

interface LoadCompanyUsecase {

    fun loadMinimumCompanyList(idx: Int, size: Int): Page<MinimumCompanyResponse>
    fun loadCustomizedMinimumCompanyList(idx: Int, size: Int, userEmail: String): Page<MinimumCompanyResponse>
    fun loadMinimumCompanyListByYear(idx: Int, size: Int, year: Int): Page<MinimumCompanyResponse>
    fun loadMaximumCompany(companyNumber: String): MaximumCompanyResponse
    fun loadCompanyDto(companyNumber: String): CompanyDto?
    fun searchCompany(idx: Int, size: Int, query: String): Page<MinimumCompanyResponse>
    fun loadCompanyThumbnailList(companyNumber: String): MutableList<String>
}
