package com.info.info_v2_backend.file.application.service

import com.info.info_v2_backend.common.file.dto.AnnouncementFileResponse
import com.info.info_v2_backend.file.adapter.output.persistence.repository.AnnouncementRepository
import com.info.info_v2_backend.file.application.port.input.announce.LoadAnnouncementFileUsecase
import org.springframework.stereotype.Service

@Service
class LoadAnnouncementFile(
    private val announcementRepository: AnnouncementRepository
): LoadAnnouncementFileUsecase {

    override fun load(announcementId: Long): List<AnnouncementFileResponse> {
        return announcementRepository.findByAnnouncementId(announcementId).map {
            it.toAnnouncementFileResponse()
        }
    }
}