package com.info.info_v2_backend.user.adapter.output.persistence.repository

import com.info.info_v2_backend.user.domain.profile.ProfilePhoto
import org.springframework.data.jpa.repository.JpaRepository

interface ProfilePhotoRepository: JpaRepository<ProfilePhoto, Int> {


}