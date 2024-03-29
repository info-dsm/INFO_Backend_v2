package com.info.info_v2_backend.company.application.port.output.notice

import com.info.info_v2_backend.common.notice.NoticeDto

interface NoticePort {

    fun loadAvailableNoticeByCompanyNumber(companyNumber: String): List<NoticeDto>
}
