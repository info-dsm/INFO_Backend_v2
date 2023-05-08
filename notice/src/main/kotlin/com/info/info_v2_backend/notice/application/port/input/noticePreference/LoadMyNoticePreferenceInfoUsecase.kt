package com.info.info_v2_backend.notice.application.port.input.noticePreference

interface LoadMyNoticePreferenceInfoUsecase {

    fun load(userEmail: String): String?
}
