package com.info.info_v2_backend.notice.adapter.input.rest.dto.request.welfare

data class EditWelfareRequest(
    val dormitorySupport: Boolean?,
    val selfDevelopmentPay: Boolean?,
    val equipmentSupport: Boolean?,
    val youthTomorrowChaeumDeduction: Boolean?,
    val alternativeMilitaryPlan: Boolean?,
    var elseSupport: String?
)