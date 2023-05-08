package com.info.info_v2_backend.notice.adapter.input.rest.dto.response

import com.info.info_v2_backend.common.file.dto.response.AttachmentResponse
import com.info.info_v2_backend.notice.adapter.input.rest.dto.request.pay.PayRequest

data class AdminMaximumNoticeResponse(
    val data: MaximumNoticeResponse,
    val pay: PayRequest

) {
    fun addAllAttachmentFileList(attachmentFileList: MutableList<AttachmentResponse>) {
        this.data.attachmentFileList = attachmentFileList
    }

}
