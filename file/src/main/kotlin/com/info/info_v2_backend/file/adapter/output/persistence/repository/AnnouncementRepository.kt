package com.info.info_v2_backend.file.adapter.output.persistence.repository

import com.info.info_v2_backend.file.domain.announcement.Announcement
import org.springframework.data.jpa.repository.JpaRepository

interface AnnouncementRepository: JpaRepository<Announcement, String> {

    fun findByAnnouncementId(announcementId: Long): List<Announcement>
}