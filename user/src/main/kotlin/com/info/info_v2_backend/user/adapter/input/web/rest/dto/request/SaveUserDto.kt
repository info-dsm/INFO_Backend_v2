package com.info.info_v2_backend.user.adapter.input.web.rest.dto.request

open class SaveUserDto(
    val name: String,
    val email: String,
    var password: String
) {

    fun hashPassword(password: String) {
        this.password = password
    }

}