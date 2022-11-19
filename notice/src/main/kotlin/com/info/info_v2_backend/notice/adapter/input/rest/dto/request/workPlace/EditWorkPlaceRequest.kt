package com.info.info_v2_backend.notice.adapter.input.rest.dto.request.workPlace


data class EditWorkPlaceRequest(
    val isSameWithCompanyAddress: Boolean?,
    val otherPlace: String?
)
