package com.info.info_v2_backend.file.domain.applies

import com.info.info_v2_backend.common.file.dto.FileDto
import com.info.info_v2_backend.file.domain.File
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import javax.persistence.DiscriminatorValue
import javax.persistence.Embedded
import javax.persistence.Entity

@Entity
@DiscriminatorValue("resume_file")
@OnDelete(action = OnDeleteAction.CASCADE)
class Resume(
    id: String,
    dto: FileDto,
    notice: ResumeNotice,
    uploader: ResumeStudent
): File(
    id,
    dto.fileUrl,
    dto.fileType,
    dto.extension,
    dto.fileName
) {

    @Embedded
    var notice: ResumeNotice = notice
        protected set

    @Embedded
    var student: ResumeStudent = uploader
        protected set

}