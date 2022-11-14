package com.info.info_v2_backend.email.adapter.output.persistence.adapter

import com.info.info_v2_backend.email.adapter.output.persistence.repository.EmailRecordRepository
import com.info.info_v2_backend.email.application.port.output.EmailRecordPersistencePort
import com.info.info_v2_backend.email.domain.EmailRecord
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Service
class EmailRecordPersistencePortAdapter(
    private val emailRecordRepository: EmailRecordRepository,
): EmailRecordPersistencePort {

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    override fun save(emailRecord: EmailRecord): EmailRecord {
        val record = emailRecordRepository.save(emailRecord)
        return record
    }

}