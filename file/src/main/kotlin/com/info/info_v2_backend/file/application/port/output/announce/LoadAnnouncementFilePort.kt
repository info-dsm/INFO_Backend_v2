package com.info.info_v2_backend.file.application.port.output.announce

import com.info.info_v2_backend.common.file.dto.AnnouncementFileResponse
import com.info.info_v2_backend.common.file.dto.response.PresignedUrlListResponse

interface LoadAnnouncementFilePort {

    fun load(announcementId: Long): List<AnnouncementFileResponse>
}