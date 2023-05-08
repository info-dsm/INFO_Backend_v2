package com.info.info_v2_backend.file.adapter.output.persistence

import com.info.info_v2_backend.common.file.dto.response.UserFileResponse
import com.info.info_v2_backend.file.adapter.output.persistence.repository.UserProfilePhotoRepository
import com.info.info_v2_backend.file.application.port.output.user.LoadUserFilePort
import com.info.info_v2_backend.file.application.port.output.user.SaveUserFilePort
import com.info.info_v2_backend.file.domain.user.UserProfilePhoto
import org.springframework.stereotype.Service


@Service
class UserFileAdapter(
    private val userProfilePhotoRepository: UserProfilePhotoRepository
): SaveUserFilePort, LoadUserFilePort {
    override fun saveProfilePhoto(userProfilePhoto: UserProfilePhoto) {
        userProfilePhotoRepository.save(userProfilePhoto)
    }

    override fun load(userEmail: String): UserProfilePhoto? {
        return userProfilePhotoRepository.findByUserEmail(userEmail).orElse(null)
    }


}
