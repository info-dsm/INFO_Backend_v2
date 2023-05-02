package com.info.info_v2_backend.notice.application.port.output.noticePreference

import com.info.info_v2_backend.notice.domain.noticePreference.NoticePreference

interface SaveNoticePreferencePort {

    fun merge(noticePreference: NoticePreference)
}
