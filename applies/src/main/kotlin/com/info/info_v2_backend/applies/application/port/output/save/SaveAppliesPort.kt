package com.info.info_v2_backend.applies.application.port.output.save

import com.info.info_v2_backend.applies.domain.Applies

interface SaveAppliesPort {

    fun save(applies: Applies): Applies
}