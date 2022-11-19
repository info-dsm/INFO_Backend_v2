package com.info.info_v2_backend.employment.adapter.output.rest

import com.info.info_v2_backend.common.notice.NoticeDto
import org.springframework.cloud.openfeign.FallbackFactory
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service

@Component
class NoticeFeignClientFallback: FallbackFactory<NoticeFeignClient> {
    override fun create(cause: Throwable?): NoticeFeignClient {
        return object : NoticeFeignClient {
            override fun loadNotice(noticeId: String): NoticeDto? {
                return null
            }

        }
    }
}