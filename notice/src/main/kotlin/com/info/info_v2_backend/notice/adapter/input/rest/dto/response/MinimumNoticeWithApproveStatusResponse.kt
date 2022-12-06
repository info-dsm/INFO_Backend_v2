package com.info.info_v2_backend.notice.adapter.input.rest.dto.response

import com.info.info_v2_backend.notice.domain.status.NoticeWaitingStatus

data class MinimumNoticeWithApproveStatusResponse (
    val notice: MinimumNoticeResponse,
    val approveStatus: NoticeWaitingStatus
)