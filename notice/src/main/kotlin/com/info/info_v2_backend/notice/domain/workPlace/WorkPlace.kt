package com.info.info_v2_backend.notice.domain.workPlace

import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.common.exception.ErrorCode
import com.info.info_v2_backend.notice.adapter.input.rest.dto.request.workPlace.EditWorkPlaceRequest
import com.info.info_v2_backend.notice.adapter.input.rest.dto.request.workPlace.WorkPlaceRequest
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class WorkPlace(
    isSameWithCompanyAddress: Boolean,
    otherPlace: String?
) {

    @Column(name = "is_same_with_company_address", nullable = false)
    var isSameWithCompanyAddress: Boolean = isSameWithCompanyAddress
        protected set

    @Column(name = "other_place", nullable = true)
    var otherPlace: String? = otherPlace
        protected set

    init {
        if (!this.isSameWithCompanyAddress && otherPlace.isNullOrEmpty()) throw BusinessException("장소 주소가 입력되지 않았습니다.", ErrorCode.INVALID_INPUT_DATA_ERROR)
    }

    fun toWorkPlaceRequest(): WorkPlaceRequest {
        return WorkPlaceRequest(
            this.isSameWithCompanyAddress,
            this.otherPlace
        )
    }

    fun editWorkPlace(r: EditWorkPlaceRequest) {
        r.isSameWithCompanyAddress?.let {
            this.isSameWithCompanyAddress = r.isSameWithCompanyAddress
        }
        this.otherPlace = r.otherPlace
    }

}