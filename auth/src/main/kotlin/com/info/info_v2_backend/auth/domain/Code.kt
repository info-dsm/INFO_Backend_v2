package com.info.info_v2_backend.auth.domain

import com.info.info_v2_backend.common.auth.AuthenticationCodeType
import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.TimeToLive
import org.springframework.data.redis.core.index.Indexed

@RedisHash
class Code(
    identifyKey: String,
    data: String,
    timeToLive: Long,
    type: String
) {

    @Id
    var identifyKey: String = identifyKey.plus(type)

    @Indexed
    var targetEmail: String = identifyKey

    val data: String = data

    @Indexed
    val type: String = type

    @TimeToLive
    var timeToLive: Long = timeToLive


}
