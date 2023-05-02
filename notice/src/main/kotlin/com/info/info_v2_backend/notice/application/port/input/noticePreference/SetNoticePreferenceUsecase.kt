package com.info.info_v2_backend.notice.application.port.input.noticePreference

interface SetNoticePreferenceUsecase {

    fun setNoticePreference(smallClassification: String, userEmail: String)
}
