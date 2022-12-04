package com.info.info_v2_backend.applies.adapter.output.persistence

import com.info.info_v2_backend.applies.adapter.output.persistence.repository.AppliesRepository
import com.info.info_v2_backend.applies.application.port.output.applies.SaveAppliesPort
import com.info.info_v2_backend.applies.domain.Applies
import org.springframework.stereotype.Service

@Service
class AppliesJpaAdapter(
    private val appliesRepository: AppliesRepository,
): SaveAppliesPort {

    override fun save(applies: Applies): Applies {
        return appliesRepository.save(applies)
    }


}