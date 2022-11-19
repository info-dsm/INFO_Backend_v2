package com.info.info_v2_backend.applies.adapter.input.rest.dto.respnose

import com.info.info_v2_backend.applies.domain.status.AppliesStatus

data class AppliesResponse(
    val appliesId: String,
    val applier: ApplierResponse,
    val noticeId: String,
    val status: AppliesStatus

) {
    data class ApplierResponse (
        val email: String,
        val name: String
    )
}
