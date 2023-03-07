package com.info.info_v2_backend.notice.adapter.input.rest.conifguration

import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.EnableCaching
import org.springframework.cache.concurrent.ConcurrentMapCacheManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
@EnableCaching
class CachingConfiguration {
    @Bean
    fun cacheManager(): CacheManager {
        return ConcurrentMapCacheManager("memberCacheStore")
    }

}