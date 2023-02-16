package com.info.info_v2_backend.file.domain.applies

import com.info.info_v2_backend.common.file.dto.FileDto
import com.info.info_v2_backend.file.domain.File
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import javax.persistence.Column
import javax.persistence.DiscriminatorValue
import javax.persistence.Embedded
import javax.persistence.Entity

@Entity
@DiscriminatorValue("resume_file")
@OnDelete(action = OnDeleteAction.CASCADE)
class Resume(
    id: String,
    dto: FileDto,
    noticeId: String,
    studentEmail: String
): File(
    id,
    dto.fileUrl,
    dto.fileType,
    dto.extension,
    dto.fileName
) {

    @Column(name = "notice_id", nullable = false)
    val noticeId: String = noticeId

    @Column(name = "resume_student_email", nullable = false)
    val studentEmail: String = studentEmail

}