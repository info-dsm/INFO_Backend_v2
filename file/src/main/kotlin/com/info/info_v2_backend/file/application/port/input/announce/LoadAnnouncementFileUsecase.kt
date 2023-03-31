package com.info.info_v2_backend.file.application.port.input.announce

import com.info.info_v2_backend.common.file.dto.AnnouncementFileResponse

interface LoadAnnouncementFileUsecase {

    fun load(announcementId: Long): List<AnnouncementFileResponse>
}