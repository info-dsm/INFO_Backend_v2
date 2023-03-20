package com.info.info_v2_backend.user.application.port.input

interface ChangePasswordUsecase {

    fun change(email: String, password: String)
}