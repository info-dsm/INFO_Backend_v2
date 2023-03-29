package com.info.info_v2_backend.statistics.business.service

import com.info.info_v2_backend.common.file.dto.response.PresignedUrlListResponse
import com.info.info_v2_backend.statistics.presentation.dto.request.CreateAnnouncementRequest
import com.info.info_v2_backend.statistics.presentation.dto.response.MaximumAnnouncementResponse
import com.info.info_v2_backend.statistics.presentation.dto.response.MinimumAnnouncementResponse
import org.springframework.data.domain.Page

interface AnnouncementService {

    fun createAnnouncment(request: CreateAnnouncementRequest): PresignedUrlListResponse

    fun getAnnounceList(idx: Int, size: Int): Page<MinimumAnnouncementResponse>
    fun getAnnounce(id: Long): MaximumAnnouncementResponse
}