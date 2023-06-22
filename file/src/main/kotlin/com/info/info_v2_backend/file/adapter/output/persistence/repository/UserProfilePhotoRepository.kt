package com.info.info_v2_backend.file.adapter.output.persistence.repository

import com.info.info_v2_backend.file.domain.user.UserProfilePhoto
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.Optional

interface UserProfilePhotoRepository: JpaRepository<UserProfilePhoto, String> {

    @Query(
        nativeQuery = true,
        value = "select * from file a, user_profile_photo b " +
                "where a.file_id = b.file_id " +
                "and user_email = :userEmail " +
                "and file_is_deleted = false " +
                "order by created_at desc limit 1")
    fun findByUserEmail(userEmail: String): Optional<UserProfilePhoto>
}
