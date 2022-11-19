package com.info.info_v2_backend.notice.application.port.output

import com.info.info_v2_backend.notice.domain.Notice

interface SaveNoticePort {

    fun saveNotice(notice: Notice)
}