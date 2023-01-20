package com.info.info_v2_backend.common.user

enum class Role(
    val level: Int,
    val mean: String
) {
    BLOCK(0, "BLOCK"),
    STUDENT(1, "STUDENT"),
    CONTACTOR(2, "CONTACTOR"),
    TEACHER(3, "TEACHER"),
    SYSTEM(4, "SYSTEM")

}