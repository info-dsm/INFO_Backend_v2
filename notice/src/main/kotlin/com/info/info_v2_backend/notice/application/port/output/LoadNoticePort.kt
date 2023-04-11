package com.info.info_v2_backend.notice.application.port.output

import com.info.info_v2_backend.notice.domain.Notice
import org.springframework.data.domain.Page

interface LoadNoticePort {

    fun loadNotice(noticeId: String): Notice?
    fun loadNoticeBySmallClassification(smallClassification: String, idx: Int, size: Int): Page<Notice>
    fun loadNoticeByCompanyName(companyName: String, idx: Int, size: Int): Page<Notice>
    fun loadNoticeByCompanyNameAndSmallClassification(companyName: String, smallClassification: String, idx: Int, size: Int): Page<Notice>
    fun loadNoticeByCompanyNumber(companyNumber: String): List<Notice>
    fun countOpenNotice(): Int

}