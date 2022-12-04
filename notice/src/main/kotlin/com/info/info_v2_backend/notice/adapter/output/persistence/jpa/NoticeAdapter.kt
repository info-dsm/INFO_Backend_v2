package com.info.info_v2_backend.notice.adapter.output.persistence.jpa

import com.info.info_v2_backend.notice.adapter.output.persistence.jpa.repository.NoticeRepository
import com.info.info_v2_backend.notice.application.port.output.LoadNoticePort
import com.info.info_v2_backend.notice.application.port.output.RemoveNoticePort
import com.info.info_v2_backend.notice.application.port.output.SaveNoticePort
import com.info.info_v2_backend.notice.domain.Notice
import com.info.info_v2_backend.notice.domain.company.NoticeCompany
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service


@Service
class NoticeAdapter(
    private val noticeRepository: NoticeRepository
): RemoveNoticePort, SaveNoticePort, LoadNoticePort {

    override fun remove(noticeId: String) {
        noticeRepository.deleteById(noticeId)
    }

    override fun saveNotice(notice: Notice) {
        noticeRepository.save(notice)
    }

    override fun loadNotice(noticeId: String): Notice? {
        return noticeRepository.findByIdOrNull(noticeId)
    }

    override fun loadNoticeByCompany(companyNumber: String): List<Notice> {
        return noticeRepository.findByCompany(
            NoticeCompany(
                companyNumber,
                companyNumber
            )
        )
    }


}