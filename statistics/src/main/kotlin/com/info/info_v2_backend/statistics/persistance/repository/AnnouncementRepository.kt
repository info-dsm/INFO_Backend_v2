package com.info.info_v2_backend.statistics.persistance.repository

import com.info.info_v2_backend.statistics.persistance.entity.announcement.Announcement
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface AnnouncementRepository: JpaRepository<Announcement, Long> {

    @Query(nativeQuery = true,
        value = "select * from announcement order by created_at desc limit 1")
    fun findLatestAnnouncement(): Announcement
}