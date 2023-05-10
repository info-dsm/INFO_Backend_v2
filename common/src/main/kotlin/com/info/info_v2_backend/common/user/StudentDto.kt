package com.info.info_v2_backend.common.user

class StudentDto(
    val studentKey: String,
    name: String,
    email: String,
    val entranceYear: Int,
    val githubLink: String?,
    profilePhotoLink: String?
): UserDto(
    name,
    email,
    profilePhotoLink,
    Role.STUDENT
)
