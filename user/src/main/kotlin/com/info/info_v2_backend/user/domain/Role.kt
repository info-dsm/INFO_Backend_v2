package com.info.info_v2_backend.user.domain

enum class Role(
    val level: Int
) {
    BLOCK(0),
    STUDENT(1),
    CONTACTOR(2),
    TEACHER(3),

}