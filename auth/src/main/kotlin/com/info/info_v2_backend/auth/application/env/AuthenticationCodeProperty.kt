package com.info.info_v2_backend.auth.application.env

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding


@ConfigurationProperties("auth")
@ConstructorBinding
data class AuthenticationCodeProperty(
    val teacherCode: String?
)