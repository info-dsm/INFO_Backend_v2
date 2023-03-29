package com.info.info_v2_backend.statistics.persistance.entity.announcement

import com.info.info_v2_backend.common.file.dto.AnnouncementFileResponse
import com.info.info_v2_backend.statistics.presentation.dto.response.MaximumAnnouncementResponse
import com.info.info_v2_backend.statistics.presentation.dto.response.MinimumAnnouncementResponse
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Announcement(
    title: String,
    content: String,
    type: AnnouncementType
){
    @Id
    @GeneratedValue
    var id: Long? = null
        protected set

    var title: String = title
        protected set

    var content: String = content
        protected set

    val createdAt: LocalDateTime = LocalDateTime.now()

    @Enumerated(EnumType.STRING)
    val type: AnnouncementType = type

    fun toMinimumAnnouncementResponse(): MinimumAnnouncementResponse{
        return MinimumAnnouncementResponse(
            this.title,
            this.createdAt,
            this.type
        )
    }

    fun toMaximumAnnouncementResponse(fileList: List<AnnouncementFileResponse>): MaximumAnnouncementResponse{
        return MaximumAnnouncementResponse(
            this.title,
            this.content,
            fileList,
            this.createdAt,
            this.type
        )
    }

}