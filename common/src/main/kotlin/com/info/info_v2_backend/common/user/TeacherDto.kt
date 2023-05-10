package com.info.info_v2_backend.common.user

class TeacherDto(
    name: String,
    email: String,
    profilePhotoLink: String?
): UserDto(
    name,
    email,
    profilePhotoLink,
    Role.TEACHER
) {


}
