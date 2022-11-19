package com.info.info_v2_backend.applies.adapter.output.rest

import com.info.info_v2_backend.common.notice.NoticeDto
import org.springframework.cloud.openfeign.FallbackFactory
import org.springframework.stereotype.Component

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