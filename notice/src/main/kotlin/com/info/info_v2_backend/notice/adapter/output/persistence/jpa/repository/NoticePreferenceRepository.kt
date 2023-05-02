package com.info.info_v2_backend.notice.adapter.output.persistence.jpa.repository

import com.info.info_v2_backend.notice.domain.noticePreference.NoticePreference
import com.info.info_v2_backend.notice.domain.noticePreference.NoticePreferenceIdClass
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface NoticePreferenceRepository: JpaRepository<NoticePreference, NoticePreferenceIdClass> {

    fun findByUserEmail(userEmail: String): Optional<NoticePreference>
    fun deleteByUserEmail(userEmail: String): Optional<NoticePreference>
}
