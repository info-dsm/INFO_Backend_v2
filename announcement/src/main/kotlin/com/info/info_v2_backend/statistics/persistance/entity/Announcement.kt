package com.info.info_v2_backend.statistics.persistance.entity

import com.info.info_v2_backend.common.file.dto.AnnouncementFileResponse
import com.info.info_v2_backend.statistics.presentation.dto.response.MaximumAnnouncementResponse
import com.info.info_v2_backend.statistics.presentation.dto.response.MinimumAnnouncementResponse
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import javax.persistence.*

@Entity
class Announcement(
    title: String,
    content: String,
    type: AnnouncementType
){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
        protected set

    @Column(nullable = false)
    var title: String = title
        protected set

    @Column(nullable = false)
    var content: String = content
        protected set

    @Column(nullable = false)
    val createdAt: LocalDateTime = ZonedDateTime.now(ZoneId.of("Asia/Seoul")).toLocalDateTime()

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val type: AnnouncementType = type

    fun toMinimumAnnouncementResponse(): MinimumAnnouncementResponse{
        return MinimumAnnouncementResponse(
            this.id!!,
            this.title,
            this.createdAt,
            this.type
        )
    }

    fun toMaximumAnnouncementResponse(fileList: List<AnnouncementFileResponse>): MaximumAnnouncementResponse{
        return MaximumAnnouncementResponse(
            this.id!!,
            this.title,
            this.content,
            fileList,
            this.createdAt,
            this.type
        )
    }

}
