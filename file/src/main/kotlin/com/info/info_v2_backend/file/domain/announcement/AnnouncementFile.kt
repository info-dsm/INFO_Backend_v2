package com.info.info_v2_backend.file.domain.announcement

import com.info.info_v2_backend.common.file.dto.response.AnnouncementFileResponse
import com.info.info_v2_backend.common.file.dto.FileDto
import com.info.info_v2_backend.file.domain.File
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import javax.persistence.DiscriminatorValue
import javax.persistence.Entity

@Entity
@DiscriminatorValue("announcement_file")
@OnDelete(action = OnDeleteAction.CASCADE)
class AnnouncementFile(
    dto: FileDto,
    announcementId: Long
): File(
    dto.fileId,
    dto.fileUrl,
    dto.fileType,
    dto.extension,
    dto.fileName
) {
    val announcementId: Long = announcementId

    fun toAnnouncementFileResponse(): AnnouncementFileResponse {
        return AnnouncementFileResponse(
            this.id,
            this.fileUrl,
            this.fileContentType,
            this.extension,
            this.fileName,
            this.announcementId
        )
    }
}
