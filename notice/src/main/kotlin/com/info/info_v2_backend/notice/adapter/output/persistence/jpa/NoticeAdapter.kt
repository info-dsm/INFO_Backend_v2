package com.info.info_v2_backend.notice.adapter.output.persistence.jpa

import com.info.info_v2_backend.notice.adapter.output.persistence.jpa.repository.NoticeRepository
import com.info.info_v2_backend.notice.application.port.output.LoadNoticePort
import com.info.info_v2_backend.notice.application.port.output.RemoveNoticePort
import com.info.info_v2_backend.notice.application.port.output.SaveNoticePort
import com.info.info_v2_backend.notice.domain.Notice
import com.info.info_v2_backend.notice.domain.company.NoticeCompany
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service


@Service
class NoticeAdapter(
    private val noticeRepository: NoticeRepository
): RemoveNoticePort, SaveNoticePort, LoadNoticePort {

    override fun remove(noticeId: String) {
        noticeRepository.deleteById(noticeId)
    }

    override fun saveNotice(notice: Notice) {3
        noticeRepository.save(notice)
    }

    override fun loadNotice(noticeId: String): Notice? {
        return noticeRepository.findByIdOrNull(noticeId)
    }

    override fun loadNoticeBySmallClassification(smallClassification: String, idx: Int, size: Int): Page<Notice> {
        return noticeRepository.findBySmallClassification(
            smallClassification,
            PageRequest.of(idx, size)
        )
    }

    override fun loadNoticeByCompanyNumber(companyNumber: String): List<Notice> {
        return noticeRepository.findByCompanyNumber(
            companyNumber
        )
    }

    override fun loadNoticeByCompanyName(companyName: String, idx: Int, size: Int): Page<Notice> {
        return noticeRepository.findByCompanyName(
            companyName,
            PageRequest.of(idx, size)
        )
    }

    override fun countOpenNotice(): Int {
        return noticeRepository.countOpenNotice()
    }


}