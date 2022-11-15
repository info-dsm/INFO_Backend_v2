package com.info.info_v2_backend.user.adapter.input.event.dto

open class UserDto(
    val name: String,
    val email: String,
    var password: String
) {

    fun hashPassword(password: String) {
        this.password = password
    }

}