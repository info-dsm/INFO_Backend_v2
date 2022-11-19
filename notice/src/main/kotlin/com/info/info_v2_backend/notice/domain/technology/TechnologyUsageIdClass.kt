package com.info.info_v2_backend.notice.domain.technology

import java.io.Serializable

data class TechnologyUsageIdClass(
    var technology: String? = null,
    var notice: String? = null
): Serializable