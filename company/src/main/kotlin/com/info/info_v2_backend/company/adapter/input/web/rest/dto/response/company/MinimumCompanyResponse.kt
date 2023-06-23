package com.info.info_v2_backend.company.adapter.input.web.rest.dto.response.company

import com.info.info_v2_backend.company.domain.businessArea.BusinessArea
import com.info.info_v2_backend.company.domain.information.AddressInfo

data class MinimumCompanyResponse (
    val companyNumber: String,
    val contactorEmail: String,
    val companyName: String,
    val homeAddressInfo: AddressInfo,
    val businessTagged: List<BusinessArea>,
    val workerCount: Int,
    val annualSales: Long,
    val isLeading: Boolean,
    val isAssociated: Boolean,
    val latestNoticeYear: Int?,
    val totalEmployedCount: Int,
    val companyIntroductionResponse: CompanyIntroductionResponse,
    val hiringClassificationList: List<String>

)
