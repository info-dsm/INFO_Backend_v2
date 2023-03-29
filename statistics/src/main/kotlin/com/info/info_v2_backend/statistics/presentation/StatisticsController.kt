package com.info.info_v2_backend.statistics.presentation

import com.info.info_v2_backend.common.file.dto.response.PresignedUrlListResponse
import com.info.info_v2_backend.statistics.business.service.AnnouncementService
import com.info.info_v2_backend.statistics.business.service.StatisticsService
import com.info.info_v2_backend.statistics.persistance.entity.statistics.StatInformation
import com.info.info_v2_backend.statistics.presentation.dto.request.CreateAnnouncementRequest
import com.info.info_v2_backend.statistics.presentation.dto.response.MaximumAnnouncementResponse
import com.info.info_v2_backend.statistics.presentation.dto.response.MinimumAnnouncementResponse
import org.springframework.data.domain.Page
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
class StatisticsController(
    private val statisticsService: StatisticsService,
    private val announcementService: AnnouncementService
) {

    @GetMapping("/{generation}")
    fun statisticsInformation(
        @PathVariable generation: Int
    ): StatInformation {
        return statisticsService.getInfo(generation)
    }

    @PostMapping("/announcement")
    fun createAnnouncement(
        @RequestBody request: CreateAnnouncementRequest
    ): PresignedUrlListResponse {
        return announcementService.createAnnouncment(request)
    }

    @GetMapping("/announcement")
    fun getMinimumAnnouncementList(
        @RequestParam idx: Int,
        @RequestParam size: Int
    ): Page<MinimumAnnouncementResponse> {
        return announcementService.getAnnounceList(idx, size)
    }

    @GetMapping("/announcement/{announcementId}")
    fun getMaximumAnnouncement(
        @PathVariable announcementId: Long
    ): MaximumAnnouncementResponse {
        return announcementService.getAnnounce(announcementId)
    }

    @GetMapping("/announcement/latest")
    fun getLatestAnnouncement(): MinimumAnnouncementResponse {
        return announcementService.getLatestAnnouncement()
    }
}