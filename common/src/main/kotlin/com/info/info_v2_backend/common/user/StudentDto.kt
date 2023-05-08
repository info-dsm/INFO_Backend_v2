package com.info.info_v2_backend.common.user

class StudentDto(
    val studentKey: String,
    name: String,
    email: String,
    val entranceYear: Int,
    val githubLink: String?,
    val profilePhotoLink: String
): UserDto(
    name,
    email
)
