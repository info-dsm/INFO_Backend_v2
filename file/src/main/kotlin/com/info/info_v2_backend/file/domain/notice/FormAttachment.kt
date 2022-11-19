package com.info.info_v2_backend.file.domain.notice

import com.info.info_v2_backend.common.file.dto.FileDto
import com.info.info_v2_backend.file.domain.File
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.DiscriminatorValue
import javax.persistence.Embedded
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne


@Entity
@DiscriminatorValue("attachment")
@OnDelete(action = OnDeleteAction.CASCADE)
class FormAttachment(
    id: String,
    dto: FileDto,
    noticeId: AttachmentNotice,
): File(
    id,
    dto.fileUrl,
    dto.fileType,
    dto.extension,
    dto.fileName
) {

    @Embedded
    var notice: AttachmentNotice = noticeId
        protected set
}