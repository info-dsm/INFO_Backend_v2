package com.info.info_v2_backend.apiGateway.property

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConfigurationProperties("jwt")
@ConstructorBinding
class JwtProperty(
    val secretKey: String
) {
}