package com.info.info_v2_backend.company.domain.businessArea

import java.io.Serializable

data class BusinessAreaTaggedIdClass(
    var businessArea: String? = null,
    var company: String? = null
): Serializable