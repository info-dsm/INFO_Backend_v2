package com.info.info_v2_backend.file.adapter.output.persistence.repository

import com.info.info_v2_backend.file.domain.announcement.AnnouncementFile
import org.springframework.data.jpa.repository.JpaRepository

interface AnnouncementFileRepository: JpaRepository<AnnouncementFile, Long> {

    fun findByAnnouncementId(id: Long): List<AnnouncementFile>
}