package com.info.info_v2_backend.file.domain.applicant

import com.info.info_v2_backend.common.file.dto.FileDto
import com.info.info_v2_backend.file.domain.File
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import javax.persistence.*


@Entity
@DiscriminatorValue("reporter")
@OnDelete(action = OnDeleteAction.CASCADE)
class Reporter(
    id: String,
    dto: FileDto,
    studentEmail: String,
    noticeId: String
): File(
    id,
    dto.fileUrl,
    dto.fileType,
    dto.extension,
    dto.fileName
) {
    var studentEmail: String = studentEmail
        protected set

    @Column(name = "notice_id", nullable = false)
    var noticeId: String = noticeId
        protected set


}