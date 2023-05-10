package com.info.info_v2_backend.file.application.service.announcement

import com.info.info_v2_backend.common.file.dto.response.AnnouncementFileResponse
import com.info.info_v2_backend.file.application.port.input.announce.LoadAnnouncementFileUsecase
import com.info.info_v2_backend.file.application.port.output.announce.LoadAnnouncementFilePort
import org.springframework.stereotype.Service

@Service
class LoadAnnouncementFile(
    private val loadAnnouncementFilePort: LoadAnnouncementFilePort
): LoadAnnouncementFileUsecase {

    override fun load(announcementId: Long): List<AnnouncementFileResponse> {
        return loadAnnouncementFilePort.load(announcementId)
    }
}
