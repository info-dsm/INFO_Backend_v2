package com.info.info_v2_backend.statistics.persistance.repository

import com.info.info_v2_backend.statistics.persistance.entity.announcement.Announcement
import org.springframework.data.jpa.repository.JpaRepository

interface AnnouncementRepository: JpaRepository<Announcement, Long> {

}