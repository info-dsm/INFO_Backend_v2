package com.info.info_v2_backend.notice.application.port.output

import com.info.info_v2_backend.notice.domain.Notice
import org.springframework.data.domain.Page

interface LoadNoticePort {

    fun loadNotice(noticeId: String): Notice?

    fun loadNoticePageBySmallClassification(smallClassification: String, idx: Int, size: Int): Page<Notice>
    fun loadNoticePageByCompanyName(companyName: String, idx: Int, size: Int): Page<Notice>
    fun loadNoticePageByCompanyNameAndSmallClassification(companyName: String, smallClassification: String, idx: Int, size: Int): Page<Notice>
    fun loadNoticeListByCompanyNumber(companyNumber: String): List<Notice>
    fun countOpenNotice(): Int

}
