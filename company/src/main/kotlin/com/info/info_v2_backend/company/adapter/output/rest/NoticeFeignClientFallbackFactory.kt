package com.info.info_v2_backend.company.adapter.output.rest

import com.info.info_v2_backend.common.notice.NoticeDto
import org.springframework.cloud.openfeign.FallbackFactory
import org.springframework.stereotype.Component

@Component
class NoticeFeignClientFallbackFactory: FallbackFactory<NoticeFeignClient> {
    override fun create(cause: Throwable?): NoticeFeignClient {
        return object : NoticeFeignClient {
            override fun loadNoticeByCompanyNumber(companyNumber: String): NoticeDto? {
                return null
            }
        }
    }
}
