package com.info.info_v2_backend.file.domain.user

import com.info.info_v2_backend.common.file.dto.FileDto
import com.info.info_v2_backend.common.file.dto.response.UserFileResponse
import com.info.info_v2_backend.common.file.dto.type.FileType
import com.info.info_v2_backend.file.domain.File
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import javax.persistence.Column
import javax.persistence.DiscriminatorValue
import javax.persistence.Entity

@Entity
@DiscriminatorValue("user_profile_photo")
@OnDelete(action = OnDeleteAction.CASCADE)
class UserProfilePhoto(
    dto: FileDto,
    userEmail: String
): File(
    dto.fileId,
    dto.fileUrl,
    dto.fileType,
    dto.extension,
    dto.fileName
) {
    @Column(name = "user_email", nullable = false)
    var userEmail: String = userEmail


    fun toUserFileResponse(): UserFileResponse {
        return UserFileResponse(
            this.id,
            this.fileUrl,
            this.fileContentType,
            this.extension,
            this.fileName,
            this.userEmail
        )
    }

}
