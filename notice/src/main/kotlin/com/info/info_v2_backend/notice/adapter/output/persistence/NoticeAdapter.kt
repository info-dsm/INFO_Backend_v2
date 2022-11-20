package com.info.info_v2_backend.notice.adapter.output.persistence

import com.info.info_v2_backend.notice.adapter.output.persistence.repository.NoticeRepository
import com.info.info_v2_backend.notice.application.port.output.LoadNoticePort
import com.info.info_v2_backend.notice.application.port.output.RemoveNoticePort
import com.info.info_v2_backend.notice.application.port.output.SaveNoticePort
import com.info.info_v2_backend.notice.domain.Notice
import com.info.info_v2_backend.notice.domain.status.NoticeWaitingStatus
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.LocalDate


@Service
class NoticeAdapter(
    private val noticeRepository: NoticeRepository
): RemoveNoticePort, SaveNoticePort {

    override fun remove(noticeId: String) {
        noticeRepository.deleteById(noticeId)
    }

    override fun saveNotice(notice: Notice) {
        noticeRepository.save(notice)
    }


}