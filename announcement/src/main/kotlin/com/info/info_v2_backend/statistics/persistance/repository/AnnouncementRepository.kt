package com.info.info_v2_backend.statistics.persistance.repository

import com.info.info_v2_backend.statistics.persistance.entity.Announcement
import com.info.info_v2_backend.statistics.persistance.entity.AnnouncementType
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface AnnouncementRepository: JpaRepository<Announcement, Long> {

    @Query(nativeQuery = true,
        value = "select * from announcement order by created_at desc limit 1")
    fun findLatestAnnouncement(): Announcement

    fun findAllByType(type: AnnouncementType, pageable: Pageable): Page<Announcement>
}
