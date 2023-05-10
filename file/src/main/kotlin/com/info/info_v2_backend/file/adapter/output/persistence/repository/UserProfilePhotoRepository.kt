package com.info.info_v2_backend.file.adapter.output.persistence.repository

import com.info.info_v2_backend.file.domain.user.UserProfilePhoto
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface UserProfilePhotoRepository: JpaRepository<UserProfilePhoto, String> {

    fun findByUserEmail(userEmail: String): Optional<UserProfilePhoto>
}
