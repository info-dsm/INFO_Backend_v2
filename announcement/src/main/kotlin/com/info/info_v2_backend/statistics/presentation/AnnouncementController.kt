package com.info.info_v2_backend.statistics.presentation

import com.info.info_v2_backend.common.auth.Auth
import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.common.exception.ErrorCode
import com.info.info_v2_backend.common.file.dto.response.PresignedUrlListResponse
import com.info.info_v2_backend.statistics.business.service.AnnouncementService
import com.info.info_v2_backend.statistics.persistance.entity.AnnouncementType
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
class AnnouncementController(
    private val announcementService: AnnouncementService
) {

    @PostMapping
    fun createAnnouncement(
        @RequestBody request: CreateAnnouncementRequest
    ): PresignedUrlListResponse {
        if (Auth.checkIsTeacher())
            return announcementService.createAnnouncment(request)
        else throw BusinessException(errorCode = ErrorCode.NO_AUTHORIZATION_ERROR)
    }

    @GetMapping
    fun getMinimumAnnouncementListByType(
        @RequestParam idx: Int,
        @RequestParam size: Int,
        @RequestParam type: AnnouncementType?
    ): Page<MinimumAnnouncementResponse> {
        return announcementService.getAnnounceList(idx, size, type)
    }

    @GetMapping("/{announcementId}")
    fun getMaximumAnnouncement(
        @PathVariable announcementId: Long
    ): MaximumAnnouncementResponse {
        return announcementService.getAnnounce(announcementId)
    }

    @GetMapping("/latest")
    fun getLatestAnnouncement(): MinimumAnnouncementResponse {
        return announcementService.getLatestAnnouncement()
    }
}
