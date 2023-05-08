package com.info.info_v2_backend.notice.application.service.noticePreference

import com.info.info_v2_backend.notice.application.port.input.noticePreference.LoadMyNoticePreferenceInfoUsecase
import com.info.info_v2_backend.notice.application.port.output.noticePreference.LoadNoticePreferencePort
import org.springframework.stereotype.Service

@Service
class LoadMyNoticePreferenceInfo(
    private val loadNoticePreferencePort: LoadNoticePreferencePort
) : LoadMyNoticePreferenceInfoUsecase {

    override fun load(userEmail: String): String? {
        return loadNoticePreferencePort.loadMyNoticePreferenceInfo(userEmail)?.let {
            it.smallClassification.name
        }
    }
}
