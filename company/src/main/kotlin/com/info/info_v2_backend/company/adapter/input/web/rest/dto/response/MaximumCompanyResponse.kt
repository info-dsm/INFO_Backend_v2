package com.info.info_v2_backend.company.adapter.input.web.rest.dto.response

import com.info.info_v2_backend.company.adapter.input.web.rest.dto.request.register.CompanyInformationRequest
import com.info.info_v2_backend.company.domain.businessArea.BusinessArea
import com.info.info_v2_backend.user.adapter.input.web.rest.dto.response.ContactorResponse

data class MaximumCompanyResponse(
    val companyNumber: String,
    val companyName: String,
    val contactor: ContactorResponse,
    val companyInformation: CompanyInformationRequest,
    val businessTagged: List<BusinessArea>,
    val isLeading: Boolean,
    val isAssociated: Boolean,
    val latestNoticeYear: Int?,
//    val totalHiredStudentList: List<HiredStudentResponse>,
    val companyIntroductionResponse: CompanyIntroductionResponse,
)