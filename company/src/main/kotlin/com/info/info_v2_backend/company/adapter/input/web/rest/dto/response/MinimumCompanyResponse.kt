package com.info.info_v2_backend.company.adapter.input.web.rest.dto.response

import com.info.info_v2_backend.company.domain.businessArea.BusinessArea
import com.info.info_v2_backend.company.domain.information.AddressInfo
import java.time.Year

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
//    val totalHiredStudentCount: Int,
    val companyIntroductionResponse: CompanyIntroductionResponse

)