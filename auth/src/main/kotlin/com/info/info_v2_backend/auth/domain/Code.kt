package com.info.info_v2_backend.auth.domain

import com.info.info_v2_backend.common.auth.AuthenticationCodeType
import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.TimeToLive

@RedisHash
class Code(
    identifyKey: String,
    data: String,
    timeToLive: Long,
    type: AuthenticationCodeType
) {

    @Id
    val identifyKey: String = identifyKey.plus(type.name)

    val targetEmail: String = identifyKey

    val data: String = data

    val type: AuthenticationCodeType = type

    @TimeToLive
    var ttl: Long = timeToLive

}