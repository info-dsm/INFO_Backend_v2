package com.info.info_v2_backend.employment.adapter.output.rest

import com.info.info_v2_backend.common.notice.NoticeDto
import com.info.info_v2_backend.employment.application.port.output.LoadNoticePort
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(name = "NOTICE-SERVICE", fallbackFactory = NoticeFeignClientFallback::class)
interface NoticeFeignClient: LoadNoticePort {

    @GetMapping("/available")
    override fun loadNotice(@RequestParam noticeId: String): NoticeDto?

}