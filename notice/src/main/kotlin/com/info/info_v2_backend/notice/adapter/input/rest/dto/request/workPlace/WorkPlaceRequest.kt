package com.info.info_v2_backend.notice.adapter.input.rest.dto.request.workPlace

import com.info.info_v2_backend.notice.domain.workPlace.WorkPlace

data class WorkPlaceRequest (
    val isSameWithCompanyAddress: Boolean,
    val otherPlace: String?
) {
    fun toWorkPlace(): WorkPlace {
        return WorkPlace(
            this.isSameWithCompanyAddress,
            this.otherPlace
        )
    }
}