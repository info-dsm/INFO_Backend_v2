package com.info.info_v2_backend.file.adapter.output.persistence

import com.info.info_v2_backend.common.file.dto.response.AnnouncementFileResponse
import com.info.info_v2_backend.file.adapter.output.persistence.repository.AnnouncementFileRepository
import com.info.info_v2_backend.file.application.port.output.announce.LoadAnnouncementFilePort
import com.info.info_v2_backend.file.application.port.output.announce.SaveAnnouncementFilePort
import com.info.info_v2_backend.file.domain.announcement.AnnouncementFile
import org.springframework.stereotype.Service

@Service
class AnnouncementFileAdapter(
    private val announcementFileRepository: AnnouncementFileRepository
): LoadAnnouncementFilePort, SaveAnnouncementFilePort {
    override fun load(announcementId: Long): List<AnnouncementFileResponse> {
        return announcementFileRepository.findByAnnouncementId(announcementId).map {
            it.toAnnouncementFileResponse()
        }
    }

    override fun save(announcementFile: AnnouncementFile) {
        announcementFileRepository.save(
            announcementFile
        )
    }
}
