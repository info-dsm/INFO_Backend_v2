package com.info.info_v2_backend.applies.adapter.output.rest

import com.info.info_v2_backend.applies.application.port.output.load.LoadNoticePort
import com.info.info_v2_backend.common.notice.NoticeDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(name = "NOTICE-SERVICE", fallbackFactory = NoticeFeignClientFallback::class)
interface NoticeFeignClient: LoadNoticePort {

    @GetMapping("/available")
    override fun loadAvailableNotice(@RequestParam noticeId: String): NoticeDto?

}