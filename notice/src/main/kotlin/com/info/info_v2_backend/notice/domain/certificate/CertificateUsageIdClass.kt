package com.info.info_v2_backend.notice.domain.certificate

import java.io.Serializable

data class CertificateUsageIdClass(
    var certificate: String? = null,
    var notice: Long? = null
): Serializable