package com.info.info_v2_backend.common.user

open class UserDto(
    val name: String,
    val email: String,
    val profilePhotoLink: String?,
    val role: Role
) {

}
