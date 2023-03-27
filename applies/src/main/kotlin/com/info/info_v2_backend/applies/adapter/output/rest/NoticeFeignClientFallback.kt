package com.info.info_v2_backend.applies.adapter.output.rest

import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.common.exception.ErrorCode
import com.info.info_v2_backend.common.notice.NoticeDto
import org.slf4j.LoggerFactory
import org.springframework.cloud.openfeign.FallbackFactory
import org.springframework.stereotype.Component

@Component
class NoticeFeignClientFallback(
): FallbackFactory<NoticeFeignClient> {
    private val log = LoggerFactory.getLogger(this.javaClass)

    override fun create(cause: Throwable?): NoticeFeignClient {
        return object : NoticeFeignClient {
            override fun loadAvailableNotice(noticeId: String): NoticeDto? {
                log.info("$noticeId load failed")
                throw BusinessException(errorCode = ErrorCode.PERSISTENCE_DATA_NOT_FOUND_ERROR)
            }

        }
    }

}