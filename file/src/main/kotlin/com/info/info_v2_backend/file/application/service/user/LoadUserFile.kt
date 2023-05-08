package com.info.info_v2_backend.file.application.service.user

import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.common.exception.ErrorCode
import com.info.info_v2_backend.common.file.dto.response.UserFileResponse
import com.info.info_v2_backend.file.application.port.input.user.LoadUserFileUsecase
import com.info.info_v2_backend.file.application.port.output.user.LoadUserFilePort
import org.springframework.stereotype.Service

@Service
class LoadUserFile(
    private val loadUserFilePort: LoadUserFilePort
): LoadUserFileUsecase {

    override fun load(userEmail: String): UserFileResponse {
        return loadUserFilePort.load(userEmail)?.let {
            it.toUserFileResponse()
        }?: throw BusinessException(errorCode = ErrorCode.PERSISTENCE_DATA_NOT_FOUND_ERROR)
    }
}
