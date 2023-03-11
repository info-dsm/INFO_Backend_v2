package com.info.info_v2_backend.auth.application.port.output.userFeignPort

interface ChangePasswordPort {

    fun change(email: String, password: String)
}