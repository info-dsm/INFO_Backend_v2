package com.info.info_v2_backend.file.application.service.user

import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.common.exception.ErrorCode
import com.info.info_v2_backend.common.file.dto.request.GenerateFileRequest
import com.info.info_v2_backend.common.file.dto.response.PresignedUrlResponse
import com.info.info_v2_backend.file.application.port.input.UploadFileUsecase
import com.info.info_v2_backend.file.application.port.input.user.UploadUserFileUsecase
import com.info.info_v2_backend.file.application.port.output.user.SaveUserFilePort
import com.info.info_v2_backend.file.domain.user.UserProfilePhoto
import org.springframework.stereotype.Service
import java.util.*

@Service
class UploadUserFile(
    private val saveUserFilePort: SaveUserFilePort,
    private val uploadFileUsecase: UploadFileUsecase
): UploadUserFileUsecase {

    override fun uploadUserProfilePhoto(request: GenerateFileRequest, userEmail: String): PresignedUrlResponse {
        val dto = uploadFileUsecase.upload(request, "USER_FILE/${userEmail}", "USER_PROFILE_PHOTO")
        val announcementFile = UserProfilePhoto(
            dto,
            userEmail
        )
        saveUserFilePort.saveProfilePhoto(
            announcementFile
        )
        return PresignedUrlResponse(
            dto.authenticatedUri?: throw BusinessException(errorCode = ErrorCode.UNDEFINED_ERROR),
            dto.fileName
        )
    }

}
