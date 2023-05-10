package com.info.info_v2_backend.notice.application.port.output.noticePreference

import com.info.info_v2_backend.notice.domain.noticePreference.NoticePreference

interface LoadNoticePreferencePort {

    fun loadMyNoticePreferenceInfo(userEmail: String): NoticePreference?
    fun loadNoticePreference(userEmail: String): NoticePreference?
}
