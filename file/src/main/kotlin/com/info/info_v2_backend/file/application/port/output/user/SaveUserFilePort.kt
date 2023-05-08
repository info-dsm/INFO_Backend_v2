package com.info.info_v2_backend.file.application.port.output.user

import com.info.info_v2_backend.file.domain.user.UserProfilePhoto

interface SaveUserFilePort {

    fun saveProfilePhoto(userProfilePhoto: UserProfilePhoto)
}
