package com.info.info_v2_backend.notice.domain.language

import java.io.Serializable

data class LanguageUsageIdClass(
    var language: String? = null,
    var notice: String? = null
): Serializable