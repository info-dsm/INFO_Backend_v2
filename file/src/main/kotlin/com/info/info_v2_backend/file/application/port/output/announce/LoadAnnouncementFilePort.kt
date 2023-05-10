package com.info.info_v2_backend.file.application.port.output.announce

import com.info.info_v2_backend.common.file.dto.response.AnnouncementFileResponse

interface LoadAnnouncementFilePort {

    fun load(announcementId: Long): List<AnnouncementFileResponse>
}
