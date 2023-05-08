package com.info.info_v2_backend.company.adapter.output.rest

import com.info.info_v2_backend.common.notice.NoticeDto
import com.info.info_v2_backend.company.application.port.output.notice.NoticePort
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(name = "NOTICE-SERVICE", )
interface NoticeFeignClient: NoticePort {

    @GetMapping("/company")
    override fun loadNoticeByCompanyNumber(@RequestParam companyNumber: String): NoticeDto?
}
