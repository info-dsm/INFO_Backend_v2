package com.info.info_v2_backend.user.domain.profile

import java.util.Random
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity(name = "profile_photo")
class ProfilePhoto(
    id: Int
) {
    @Id
    val id: Int = id
    @Column(name = "url")
    val url: String = ""

}