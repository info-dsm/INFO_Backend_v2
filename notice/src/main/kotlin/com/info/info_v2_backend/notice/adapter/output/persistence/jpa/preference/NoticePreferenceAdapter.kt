package com.info.info_v2_backend.notice.adapter.output.persistence.jpa.preference

import com.info.info_v2_backend.notice.adapter.output.persistence.jpa.repository.NoticePreferenceRepository
import com.info.info_v2_backend.notice.application.port.output.noticePreference.LoadNoticePreferencePort
import com.info.info_v2_backend.notice.application.port.output.noticePreference.SaveNoticePreferencePort
import com.info.info_v2_backend.notice.domain.noticePreference.NoticePreference
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
@Transactional
class NoticePreferenceAdapter(
    private val noticePreferenceRepository: NoticePreferenceRepository
): LoadNoticePreferencePort, SaveNoticePreferencePort {
    override fun loadMyNoticePreferenceInfo(userEmail: String): NoticePreference? {
        return noticePreferenceRepository.findByUserEmail(userEmail).orElse(null)
    }

    override fun loadNoticePreference(userEmail: String): NoticePreference? {
        return noticePreferenceRepository.findByUserEmail(userEmail).orElse(null)
    }

    override fun merge(noticePreference: NoticePreference) {
        noticePreferenceRepository.findByUserEmail(noticePreference.userEmail).orElse(null)?.let {
            noticePreferenceRepository.deleteByUserEmail(it.userEmail)
        }
        noticePreferenceRepository.save(noticePreference)
    }

}
